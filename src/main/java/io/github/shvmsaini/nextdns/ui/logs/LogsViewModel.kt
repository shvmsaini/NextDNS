package io.github.shvmsaini.nextdns.ui.logs

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.shvmsaini.nextdns.data.repository.LogsRepository
import io.github.shvmsaini.nextdns.domain.model.LogsUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LogsViewModel @Inject constructor(
    private val logsRepository: LogsRepository
) : ViewModel() {
    private val _logsUiState: MutableStateFlow<LogsUiState> =
        MutableStateFlow(LogsUiState.Loading)

    val logsUiState: StateFlow<LogsUiState>
        get() = _logsUiState

    fun getLogsList(id: String) {
        viewModelScope.launch {
            _logsUiState.value = LogsUiState.Loading
            logsRepository.getLogs(id).collect {
                _logsUiState.value = it
            }
        }
    }
}