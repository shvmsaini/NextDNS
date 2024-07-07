package io.github.shvmsaini.nextdns.ui.view

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import io.github.shvmsaini.nextdns.domain.model.LogsUiState
import io.github.shvmsaini.nextdns.ui.logs.LogsViewModel

@Composable
fun LogsScreen(
    modifier: Modifier,
    id: String,
    viewModel: LogsViewModel,
) {
    val viewState by viewModel.logsUiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.getLogsList(id)
    }

    when (val state = viewState) {
        LogsUiState.Error -> Error()
        LogsUiState.Loading -> Loading()
        is LogsUiState.Success -> LogsList(
            modifier = modifier,
            list = state.logsUiModel,
        )
    }
}