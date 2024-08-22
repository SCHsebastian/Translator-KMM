package com.plcoding.translator_kmm.translate.domain.translate

enum class TranslateError {
    SERVICE_UNAVAILABLE,
    SERVER_ERROR,
    CLIENT_ERROR,
    UNKNOWN_ERROR;
}

class TranslateException(val translateError: TranslateError) : Exception("An error occurred while translating: $translateError")