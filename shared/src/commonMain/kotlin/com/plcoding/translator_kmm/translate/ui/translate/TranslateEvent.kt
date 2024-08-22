package com.plcoding.translator_kmm.translate.ui.translate

import com.plcoding.translator_kmm.core.ui.history.HistoryItemDecorator
import com.plcoding.translator_kmm.core.ui.language.LanguageDecorator

sealed class TranslateEvent {
    data class ChooseFromLanguage(val language: LanguageDecorator): TranslateEvent()
    data class ChooseToLanguage(val language: LanguageDecorator): TranslateEvent()
    object StopChoosingLanguage : TranslateEvent()
    object SwapLanguages : TranslateEvent()
    data class ChangeTranslation(val text: String) : TranslateEvent()
    object Translate : TranslateEvent()
    object OpenFromLanguageDropDown : TranslateEvent()
    object OpenToLanguageDropDown : TranslateEvent()
    object CloseTranslation : TranslateEvent()
    data class SelectHistoryItem(val item: HistoryItemDecorator): TranslateEvent()
    object EditTranslation: TranslateEvent()
    object RecordAudio: TranslateEvent()
    data class SubmitVoiceResult(val result: String?): TranslateEvent()
    object StopRecording: TranslateEvent()
    data class DeleteHistoryItem(val item: HistoryItemDecorator): TranslateEvent()
    data class OnError(val error: String): TranslateEvent()
}