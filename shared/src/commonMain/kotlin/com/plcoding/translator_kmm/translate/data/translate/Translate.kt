package com.plcoding.translator_kmm.translate.data.translate

import com.plcoding.translator_kmm.core.domain.history.HistoryDataSource
import com.plcoding.translator_kmm.core.domain.history.HistoryItem
import com.plcoding.translator_kmm.core.domain.language.Language
import com.plcoding.translator_kmm.core.domain.util.Resource
import com.plcoding.translator_kmm.translate.domain.translate.TranslateClient
import com.plcoding.translator_kmm.translate.domain.translate.TranslateError

class Translate(
    private val client: TranslateClient,
    private val historyDataSource: HistoryDataSource
) {

    suspend fun execute(
        text: String,
        fromLanguage: Language,
        toLanguage: Language
    ): Resource<String>{
        return try {
            val translatedText = client.translate(text = text, fromLanguage =  fromLanguage, toLanguage =  toLanguage)
            historyDataSource.insertHistoryItem(
                HistoryItem(null, text, fromLanguage.langCode, toLanguage.langCode, translatedText)
            )
            return Resource.Success(translatedText)
        }catch (e: Exception){
            e.printStackTrace()
            Resource.Error(e)
        }
    }
}