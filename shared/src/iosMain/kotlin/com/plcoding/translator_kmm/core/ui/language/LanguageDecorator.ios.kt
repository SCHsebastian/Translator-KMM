package com.plcoding.translator_kmm.core.ui.language

import com.plcoding.translator_kmm.core.domain.language.Language

actual class LanguageDecorator(
    actual val language: Language,
    val imageName: String
) {

    actual companion object {
        actual fun byCode(langCode: String): LanguageDecorator {
            return allLanguages.find { it.language.langCode == langCode }
                ?: throw IllegalArgumentException("Invalid or unsupported language code")
        }

        actual val allLanguages: List<LanguageDecorator>
            get() = Language.values().map { language ->
                LanguageDecorator(
                    language = language,
                    imageName = language.langName.lowercase()
                )
            }
    }

}