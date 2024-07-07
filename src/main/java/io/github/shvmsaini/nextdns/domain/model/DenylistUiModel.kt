package io.github.shvmsaini.nextdns.domain.model

sealed class DenylistUiState {
    data class Success(
        val denylistUiModel: List<DenylistUiModel>
    ) : DenylistUiState()

    object Loading : DenylistUiState()
    object Error : DenylistUiState()
}

data class DenylistUiModel(
    val id: String,
    val active: Boolean,
    val favicon: String,
)