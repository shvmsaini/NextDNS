package io.github.shvmsaini.nextdns.domain.model

import io.github.shvmsaini.nextdns.service.model.LogsModel
import io.github.shvmsaini.nextdns.utils.BasicUtils.toFavicon
import io.github.shvmsaini.nextdns.utils.BasicUtils.toTimeStamp

fun toUiModel(logsModel: LogsModel): LogsUiState =
    LogsUiState.Success(logsModel.data.map {
        LogsUiModel(
            domain = it.domain ?: "",
            clientIp = it.clientIp ?: "",
            timestamp = runCatching {
                it.timestamp?.toTimeStamp()
            }.getOrNull() ?: "",
            favicon = it.domain?.toFavicon() ?: "",
        )
    })