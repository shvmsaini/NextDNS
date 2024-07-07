package io.github.shvmsaini.nextdns.data.repository

import android.util.Log
import io.github.shvmsaini.nextdns.TAG
import io.github.shvmsaini.nextdns.domain.model.LogsUiState
import io.github.shvmsaini.nextdns.domain.model.toUiModel
import io.github.shvmsaini.nextdns.service.ApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LogsRepository @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun getLogs(id: String): Flow<LogsUiState> = flow {
        kotlin.runCatching {
            emit(toUiModel(apiService.getLogs(id)))
        }.onFailure {
            Log.d(TAG, "getLogs: $it")
            emit(LogsUiState.Error)
        }
    }
}