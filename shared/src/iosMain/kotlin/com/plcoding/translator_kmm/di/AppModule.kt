package com.plcoding.translator_kmm.di

import com.plcoding.translator_kmm.core.data.history.HistoryDataSource
import com.plcoding.translator_kmm.core.domain.history.HistoryRepository
import com.plcoding.translator_kmm.database.TranslateDatabase
import com.plcoding.translator_kmm.translate.data.history.HistoryDataSourceImpl
import com.plcoding.translator_kmm.translate.data.history.HistoryRepositoryImpl
import com.plcoding.translator_kmm.translate.data.local.DatabaseDriverFactory
import com.plcoding.translator_kmm.translate.data.remote.HttpClientFactory
import com.plcoding.translator_kmm.translate.data.translate.Translate
import com.plcoding.translator_kmm.translate.data.translate.TranslateClientImpl
import com.plcoding.translator_kmm.translate.domain.translate.TranslateClient

class AppModule {

    private val historyDataSource: HistoryDataSource by lazy {
        HistoryDataSourceImpl(db = TranslateDatabase(driver = DatabaseDriverFactory().create()))
    }

    val historyRepository: HistoryRepository by lazy {
        HistoryRepositoryImpl(historyDataSource = historyDataSource)
    }

    private val translateClient: TranslateClient by lazy {
        TranslateClientImpl(httpClient = HttpClientFactory().create())
    }

    val translateUseCase: Translate by lazy {
        Translate(client = translateClient, historyRepository = historyRepository)
    }

}