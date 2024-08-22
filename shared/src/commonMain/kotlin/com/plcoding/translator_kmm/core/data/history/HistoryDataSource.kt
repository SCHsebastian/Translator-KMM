package com.plcoding.translator_kmm.core.data.history

import com.plcoding.translator_kmm.core.domain.history.HistoryItem
import kotlinx.coroutines.flow.Flow
import kotlin.coroutines.CoroutineContext

interface HistoryDataSource {
    fun getHistory(coroutineContext: CoroutineContext): Flow<List<HistoryItem>>
    suspend fun insertHistoryItem(item: HistoryItem)
    suspend fun removeHistoryItem(itemId: Long)
}