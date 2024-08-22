package com.plcoding.translator_kmm.core.ui.history

data class HistoryItemDecorator (
    val id: Long,
    val fromText: String,
    val toText: String,
    val fromLang: String,
    val toLang: String,
)