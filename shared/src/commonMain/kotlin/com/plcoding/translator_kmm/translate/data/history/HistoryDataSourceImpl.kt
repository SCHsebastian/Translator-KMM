package com.plcoding.translator_kmm.translate.data.history

import com.plcoding.translator_kmm.core.data.history.HistoryDataSource
import com.plcoding.translator_kmm.core.domain.history.HistoryItem
import com.plcoding.translator_kmm.database.TranslateDatabase
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.datetime.Clock
import kotlin.coroutines.CoroutineContext

class HistoryDataSourceImpl(
    db: TranslateDatabase
): HistoryDataSource {

    private val queries = db.translateQueries

    override fun getHistory(coroutineContext: CoroutineContext): Flow<List<HistoryItem>> {
        return queries.getHistory().asFlow().mapToList(coroutineContext).map { history ->
            history.map { it.toHistoryItem() }
        }
    }

    override suspend fun insertHistoryItem(item: HistoryItem) {
        queries.insertHistoryEntity(
            id = item.id,
            fromLanguageCode = item.fromLanguageCode,
            fromText = item.fromText,
            toLanguageCode = item.toLanguageCode,
            toText = item.toText,
            timestamp = Clock.System.now().toEpochMilliseconds()
        )
    }

    override suspend fun removeHistoryItem(itemId: Long) {
        queries.removeHistoryEntityById(itemId)
    }
}