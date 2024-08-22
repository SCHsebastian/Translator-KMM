package com.plcoding.translator_kmm.android.di

import android.app.Application
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
import com.squareup.sqldelight.db.SqlDriver
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesSqlDriver(app: Application): SqlDriver{
        return DatabaseDriverFactory(app).create()
    }


    @Provides
    @Singleton
    fun providesHistoryDataSource(driver: SqlDriver): HistoryDataSource{
        return HistoryDataSourceImpl(db = TranslateDatabase(driver))
    }

    @Provides
    @Singleton
    fun providesHistoryRepository(historyDataSource: HistoryDataSource): HistoryRepository{
        return HistoryRepositoryImpl(historyDataSource)
    }

    @Provides
    @Singleton
    fun providesHttpClient(): HttpClient{
        return HttpClientFactory().create()
    }

    @Provides
    @Singleton
    fun providesTranslateClient(httpClient: HttpClient): TranslateClient{
        return TranslateClientImpl(httpClient)
    }

    @Provides
    @Singleton
    fun providesTranslate(translateClient: TranslateClient, historyRepository: HistoryRepository): Translate{
        return Translate(client = translateClient, historyRepository = historyRepository)
    }
    
}