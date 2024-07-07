package io.github.shvmsaini.nextdns.data.repository

import io.github.shvmsaini.nextdns.domain.model.DenylistUiState
import io.github.shvmsaini.nextdns.domain.model.toUiModel
import io.github.shvmsaini.nextdns.service.ApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DenylistRepository @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun getDenylist(id: String): Flow<DenylistUiState> = flow {
        kotlin.runCatching {
            emit(toUiModel(apiService.getDenylist(id)))
        }.onFailure {
            emit(DenylistUiState.Error)
        }
    }

    suspend fun getAllowlist(id: String): Flow<DenylistUiState> = flow {
        kotlin.runCatching {
            emit(toUiModel(apiService.getAllowlist(id)))
        }.onFailure {
            emit(DenylistUiState.Error)
        }
    }
}