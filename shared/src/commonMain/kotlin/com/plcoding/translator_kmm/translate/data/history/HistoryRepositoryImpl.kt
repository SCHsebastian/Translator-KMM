package com.plcoding.translator_kmm.translate.data.history

import com.plcoding.translator_kmm.core.data.history.HistoryDataSource
import com.plcoding.translator_kmm.core.domain.history.HistoryItem
import com.plcoding.translator_kmm.core.domain.history.HistoryRepository
import com.plcoding.translator_kmm.core.domain.util.CommonFlow
import com.plcoding.translator_kmm.core.domain.util.asCommonFlow
import kotlin.coroutines.CoroutineContext

class HistoryRepositoryImpl(
    private val historyDataSource: HistoryDataSource
): HistoryRepository {

    override fun getHistory(coroutineContext: CoroutineContext): CommonFlow<List<HistoryItem>> {
        return historyDataSource.getHistory(coroutineContext).asCommonFlow()
    }

    override suspend fun insertHistoryItem(item: HistoryItem) {
        historyDataSource.insertHistoryItem(item)
    }

    override suspend fun deleteHistoryItem(itemId: Long) {
        historyDataSource.removeHistoryItem(itemId)
    }
}