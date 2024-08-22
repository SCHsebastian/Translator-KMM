package com.plcoding.translator_kmm.translate.ui.translate

import com.plcoding.translator_kmm.core.ui.history.HistoryItemDecorator
import com.plcoding.translator_kmm.core.ui.language.LanguageDecorator
import com.plcoding.translator_kmm.translate.domain.translate.TranslateError

data class TranslateState(
    val fromText: String = "",
    val toText: String? = "",
    val fromLanguage: LanguageDecorator = LanguageDecorator.byCode("es"),
    val toLanguage: LanguageDecorator = LanguageDecorator.byCode("en"),
    val isChoosingFromLanguage: Boolean = false,
    val isChoosingToLanguage: Boolean = false,
    val isTranslating: Boolean = false,
    val error: TranslateError? = null,
    val history: List<HistoryItemDecorator> = emptyList()
)