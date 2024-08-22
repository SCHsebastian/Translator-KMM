package com.plcoding.translator_kmm.core.domain.history

import com.plcoding.translator_kmm.core.domain.util.CommonFlow
import kotlin.coroutines.CoroutineContext

interface HistoryDataSource {
    fun getHistory(coroutineContext: CoroutineContext): CommonFlow<List<HistoryItem>>
    suspend fun insertHistoryItem(item: HistoryItem)
}