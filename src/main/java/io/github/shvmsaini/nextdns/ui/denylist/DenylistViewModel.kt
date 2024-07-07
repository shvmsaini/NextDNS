package io.github.shvmsaini.nextdns.ui.denylist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.shvmsaini.nextdns.data.repository.DenylistRepository
import io.github.shvmsaini.nextdns.domain.model.DenylistUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DenylistViewModel @Inject constructor(
    private val denylistRepository: DenylistRepository
) : ViewModel() {
    private val _denylistUiState: MutableStateFlow<DenylistUiState> =
        MutableStateFlow(DenylistUiState.Loading)

    val denylistUiState: StateFlow<DenylistUiState>
        get() = _denylistUiState

    fun getDenylist(id: String) {
        viewModelScope.launch {
            _denylistUiState.value = DenylistUiState.Loading
            denylistRepository.getDenylist(id).collect {
                _denylistUiState.value = it
            }
        }
    }

    private val _allowlistUiState: MutableStateFlow<DenylistUiState> =
        MutableStateFlow(DenylistUiState.Loading)

    val allowlistUiState: StateFlow<DenylistUiState>
        get() = _allowlistUiState

    fun getAllowlist(id: String) {
        viewModelScope.launch {
            _allowlistUiState.value = DenylistUiState.Loading
            denylistRepository.getAllowlist(id).collect {
                _allowlistUiState.value = it
            }
        }
    }
}