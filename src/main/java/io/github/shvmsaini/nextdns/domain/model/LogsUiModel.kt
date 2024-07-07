package io.github.shvmsaini.nextdns.domain.model

sealed class LogsUiState {
    data class Success(
        val logsUiModel: List<LogsUiModel>
    ) : LogsUiState()

    object Loading : LogsUiState()
    object Error : LogsUiState()
}

data class LogsUiModel(
    val domain: String,
    val clientIp: String,
    val timestamp: String,
    val favicon: String,
)