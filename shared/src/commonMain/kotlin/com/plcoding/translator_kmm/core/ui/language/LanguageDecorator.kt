package com.plcoding.translator_kmm.core.ui.language

import com.plcoding.translator_kmm.core.domain.language.Language

expect class LanguageDecorator {
    val  language: Language

    companion object {
        fun byCode(langCode: String): LanguageDecorator
        val allLanguages: List<LanguageDecorator>
    }

}