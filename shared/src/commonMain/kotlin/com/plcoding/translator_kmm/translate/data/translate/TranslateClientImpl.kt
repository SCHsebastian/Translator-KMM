package com.plcoding.translator_kmm.translate.data.translate

import com.plcoding.translator_kmm.NetworkConstants
import com.plcoding.translator_kmm.core.domain.language.Language
import com.plcoding.translator_kmm.translate.domain.translate.TranslateClient
import com.plcoding.translator_kmm.translate.domain.translate.TranslateError
import com.plcoding.translator_kmm.translate.domain.translate.TranslateException
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.http.ContentType
import io.ktor.http.contentType

class TranslateClientImpl(
    private val httpClient: HttpClient
): TranslateClient {

    override suspend fun translate(text: String, fromLanguage: Language, toLanguage: Language): String {
        val result = try{
            val request = HttpRequestBuilder().apply {
                url(NetworkConstants.TRANSLATOR_API_URL + "/translate")
                contentType(ContentType.Application.Json)
                setBody(
                    TranslateDTO(
                        text = text,
                        fromLanguageCode = fromLanguage.langCode,
                        toLanguageCode = toLanguage.langCode
                    )
                )
            }
            httpClient.post(request)
        } catch(e: Exception){
            throw TranslateException(TranslateError.SERVICE_UNAVAILABLE)
        }

        when(result.status.value){
            in 200 .. 299 -> Unit
            500 -> throw TranslateException(TranslateError.SERVER_ERROR)
            in 400 .. 499 -> throw TranslateException(TranslateError.CLIENT_ERROR)
            else -> throw TranslateException(TranslateError.UNKNOWN_ERROR)
        }

        return try {
            result.body<TranslatedDTO>().translatedText
        } catch(e: Exception){
            throw TranslateException(TranslateError.SERVER_ERROR)
        }
    }

}