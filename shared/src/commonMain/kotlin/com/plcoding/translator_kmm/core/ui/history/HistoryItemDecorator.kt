package com.plcoding.translator_kmm.core.ui.history

import com.plcoding.translator_kmm.core.domain.history.HistoryItem
import com.plcoding.translator_kmm.core.ui.language.LanguageDecorator

data class HistoryItemDecorator (
    val id: Long,
    val fromText: String,
    val toText: String,
    val fromLang: LanguageDecorator,
    val toLang: LanguageDecorator,
)

fun HistoryItem.toHistoryItemDecorator(): HistoryItemDecorator? {
    return HistoryItemDecorator(
        id = id ?: return null,
        fromText = fromText,
        toText = toText,
        fromLang = LanguageDecorator.byCode(fromLanguageCode),
        toLang = LanguageDecorator.byCode(toLanguageCode)
    )
}