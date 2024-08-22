package com.plcoding.translator_kmm.android.translate.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plcoding.translator_kmm.core.domain.history.HistoryRepository
import com.plcoding.translator_kmm.translate.data.translate.Translate
import com.plcoding.translator_kmm.translate.ui.translate.TranslateEvent
import com.plcoding.translator_kmm.translate.ui.translate.TranslateViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AndroidTranslateViewModel @Inject constructor(
    private val translate: Translate,
    private val historyRepository: HistoryRepository,
): ViewModel() {

    private val viewModel by lazy {
        TranslateViewModel(
            translate = translate,
            historyRepository = historyRepository,
            coroutineScope = viewModelScope
        )
    }

    val state = viewModel.state

    fun onEvent(event: TranslateEvent) {
        viewModel.onEvent(event)
    }

}