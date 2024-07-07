package io.github.shvmsaini.nextdns.ui.view

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import io.github.shvmsaini.nextdns.domain.model.DenylistUiState
import io.github.shvmsaini.nextdns.ui.denylist.DenylistViewModel

@Composable
fun DenylistScreen(
    isAllowlist: Boolean,
    modifier: Modifier,
    id: String,
    viewModel: DenylistViewModel,
) {
    val viewState by if (isAllowlist)
        viewModel.allowlistUiState.collectAsStateWithLifecycle()
    else
        viewModel.denylistUiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        if (isAllowlist) viewModel.getAllowlist(id)
        else viewModel.getDenylist(id)
    }

    when (val state = viewState) {
        DenylistUiState.Error -> Error()
        DenylistUiState.Loading -> Loading()
        is DenylistUiState.Success -> DenylistList(
            isAllowlist = isAllowlist,
            modifier = modifier,
            list = state.denylistUiModel
        )
    }
}