package com.plcoding.translator_kmm.translate.data.translate

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TranslateDTO(
    @SerialName("q") val text: String,
    @SerialName("source") val fromLanguageCode: String,
    @SerialName("target") val toLanguageCode: String
)