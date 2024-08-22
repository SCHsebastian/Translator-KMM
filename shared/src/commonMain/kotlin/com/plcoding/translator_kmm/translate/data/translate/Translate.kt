package com.plcoding.translator_kmm.translate.data.translate

import com.plcoding.translator_kmm.core.domain.history.HistoryItem
import com.plcoding.translator_kmm.core.domain.history.HistoryRepository
import com.plcoding.translator_kmm.core.domain.language.Language
import com.plcoding.translator_kmm.core.domain.util.Resource
import com.plcoding.translator_kmm.translate.domain.translate.TranslateClient

class Translate(
    private val client: TranslateClient,
    private val historyRepository: HistoryRepository
) {

    suspend fun execute(
        text: String,
        fromLanguage: Language,
        toLanguage: Language
    ): Resource<String>{
        return try {
            val translatedText = client.translate(text = text, fromLanguage =  fromLanguage, toLanguage =  toLanguage)
            historyRepository.insertHistoryItem(
                HistoryItem(null, text, fromLanguage.langCode, toLanguage.langCode, translatedText)
            )
            return Resource.Success(translatedText)
        }catch (e: Exception){
            e.printStackTrace()
            Resource.Error(e)
        }
    }
}