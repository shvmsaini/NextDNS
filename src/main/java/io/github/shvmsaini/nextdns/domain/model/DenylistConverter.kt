package io.github.shvmsaini.nextdns.domain.model

import io.github.shvmsaini.nextdns.service.model.DenylistModel
import io.github.shvmsaini.nextdns.utils.BasicUtils.toFavicon

fun toUiModel(denylistModel: DenylistModel): DenylistUiState =
    DenylistUiState.Success(denylistModel.data.map {
        DenylistUiModel(
            id = it.id ?: "",
            active = it.active ?: false,
            favicon = it.id?.toFavicon() ?: "",
        )
    })