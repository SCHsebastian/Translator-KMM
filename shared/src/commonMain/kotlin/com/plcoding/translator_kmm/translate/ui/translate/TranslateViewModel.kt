package com.plcoding.translator_kmm.translate.ui.translate

import com.plcoding.translator_kmm.core.domain.history.HistoryRepository
import com.plcoding.translator_kmm.core.domain.util.Resource
import com.plcoding.translator_kmm.core.domain.util.asCommonStateFlow
import com.plcoding.translator_kmm.core.ui.history.toHistoryItemDecorator
import com.plcoding.translator_kmm.translate.data.translate.Translate
import com.plcoding.translator_kmm.translate.domain.translate.TranslateException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class TranslateViewModel(
    private val translate: Translate,
    private val historyRepository: HistoryRepository,
    coroutineScope: CoroutineScope?
) {

    private val viewModelScope: CoroutineScope = coroutineScope ?: CoroutineScope(Dispatchers.Main)
    private val _state = MutableStateFlow(TranslateState())
    val state = combine(
        _state,
        historyRepository.getHistory(coroutineContext = viewModelScope.coroutineContext)
    ){ state, history ->
        if (state.history != history) {
            state.copy(
                history = history.mapNotNull { item ->
                    item.toHistoryItemDecorator()
                }
            )
        } else state
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), TranslateState()).asCommonStateFlow()

    private var translateJob : Job? = null

    fun translate() {
        if (state.value.isTranslating || state.value.fromText.isBlank()) {
            return
        }
        translateJob = viewModelScope.launch {
            _state.update {
                it.copy(
                    isTranslating = true
                )
            }
            val result = translate.execute(
                text = state.value.fromText,
                fromLanguage = state.value.fromLanguage.language,
                toLanguage = state.value.toLanguage.language
            )
            when(result) {
                is Resource.Success -> {
                    _state.update {
                        it.copy(
                            isTranslating = false,
                            toText = result.data
                        )
                    }
                }
                is Resource.Error -> {
                    _state.update {
                        it.copy(
                            isTranslating = false,
                            error = (result.throwable as? TranslateException)?.translateError
                        )
                    }
                }
            }

        }
    }

    fun onEvent(event: TranslateEvent) = viewModelScope.launch{
        when(event){
            is TranslateEvent.ChooseFromLanguage -> chooseFromLanguage(event)
            is TranslateEvent.ChooseToLanguage -> chooseToLanguage(event)
            is TranslateEvent.ChangeTranslation -> changeTranslation(event)
            TranslateEvent.CloseTranslation -> closeTranslation()
            is TranslateEvent.DeleteHistoryItem -> deleteHistoryItem(event)
            TranslateEvent.EditTranslation ->editTranslation()
            TranslateEvent.OpenFromLanguageDropDown -> openFromLanguageDropDown()
            TranslateEvent.OpenToLanguageDropDown -> openToLanguageDropDown()
            is TranslateEvent.SelectHistoryItem -> selectHistoryItem(event)
            TranslateEvent.StopChoosingLanguage -> stopChoosingLanguage()
            is TranslateEvent.SubmitVoiceResult -> submitVoiceResult(event)
            TranslateEvent.SwapLanguages -> swapLanguages()
            TranslateEvent.Translate -> translate()
            else -> Unit
        }
    }

    private fun swapLanguages() {
        _state.update {
            it.copy(
                fromLanguage = it.toLanguage,
                toLanguage = it.fromLanguage,
                fromText = it.toText ?: "",
                toText = if (it.toText == null) null else it.fromText
            )
        }
    }

    private fun submitVoiceResult(event: TranslateEvent.SubmitVoiceResult) {
        _state.update {
            it.copy(
                fromText = event.result ?: it.fromText,
                isTranslating = if (event.result != null) false else it.isTranslating,
                toText = if (event.result != null) null else it.toText
            )
        }
    }

    private fun stopChoosingLanguage() {
        _state.update {
            it.copy(
                isChoosingFromLanguage = false,
                isChoosingToLanguage = false
            )
        }
    }

    private fun selectHistoryItem(event: TranslateEvent.SelectHistoryItem) {
        translateJob?.cancel()
        _state.update {
            _state.value.copy(
                fromText = event.item.fromText,
                toText = event.item.toText,
                isTranslating = false,
                fromLanguage = event.item.fromLang,
                toLanguage = event.item.toLang
            )
        }
    }

    private fun openToLanguageDropDown() {
        _state.update {
            _state.value.copy(
                isChoosingToLanguage = true
            )
        }
    }

    private fun openFromLanguageDropDown() {
        _state.update {
            _state.value.copy(
                isChoosingFromLanguage = true
            )
        }
    }

    private fun editTranslation() {
        if (state.value.toText != null) {
            _state.update {
                it.copy(
                    toText = null,
                    isTranslating = false
                )
            }
        }
    }

    private fun deleteHistoryItem(event: TranslateEvent.DeleteHistoryItem) {
        viewModelScope.launch {
            historyRepository.deleteHistoryItem(event.item.id)
        }
    }

    private fun closeTranslation() {
        _state.update {
            _state.value.copy(
                isTranslating = false,
                fromText = "",
                toText = null
            )
        }
    }

    private fun changeTranslation(event: TranslateEvent.ChangeTranslation) {
        _state.update {
            _state.value.copy(
                fromText = event.text
            )
        }
    }

    private fun chooseToLanguage(event: TranslateEvent.ChooseToLanguage) {
        _state.update {
            _state.value.copy(
                isChoosingToLanguage = false,
                toLanguage = event.language
            )
        }.also {
            translate()
        }
    }

    private fun chooseFromLanguage(event: TranslateEvent.ChooseFromLanguage) {
        _state.update {
            _state.value.copy(
                isChoosingFromLanguage = false,
                fromLanguage = event.language
            )
        }
    }


}