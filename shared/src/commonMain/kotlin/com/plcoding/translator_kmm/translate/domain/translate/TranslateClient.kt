package com.plcoding.translator_kmm.translate.domain.translate

import com.plcoding.translator_kmm.core.domain.language.Language

interface TranslateClient {
    suspend fun translate(text: String, fromLanguage: Language, toLanguage: Language): String
}