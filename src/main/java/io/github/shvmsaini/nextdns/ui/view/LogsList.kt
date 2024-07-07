package io.github.shvmsaini.nextdns.ui.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import io.github.shvmsaini.nextdns.R
import io.github.shvmsaini.nextdns.domain.model.LogsUiModel

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LogsListPreview() {
    LogsListItem(
        0, 0,
        LogsUiModel(
            domain = "collector-pxdojv695v.protechts.net",
            clientIp = "120.0.0.1",
            timestamp = "2024-06-18T13:23:15.800Z",
            favicon = "",
        )
    )
}

@Composable
fun LogsList(
    modifier: Modifier,
    list: List<LogsUiModel>,
) {
    Surface(color = MaterialTheme.colorScheme.background, modifier = modifier.fillMaxSize()) {
        LazyColumn {
            items(count = list.size, itemContent = { index ->
                LogsListItem(index, list.size, list[index])
            })
        }
    }
}

@Composable
fun LogsListItem(
    pos: Int,
    size: Int,
    uiModel: LogsUiModel,
) {
    var detailsVisible by remember { mutableStateOf(false) }
    Card(
        shape = RoundedCornerShape(
            topStart = if (size == 1 || pos == 0) 10.dp else 4.dp,
            topEnd = if (size == 1 || pos == 0) 10.dp else 4.dp,
            bottomStart = if (size == 1 || pos == size - 1) 10.dp else 4.dp,
            bottomEnd = if (size == 1 || pos == size - 1) 10.dp else 4.dp,
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = 8.dp,
                end = 8.dp,
                top = if (pos == 0) 8.dp else 1.dp,
                bottom = 1.dp
            ),
    ) {
        Column(
            modifier = Modifier,
            verticalArrangement = Arrangement.Center,
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        detailsVisible = !detailsVisible
                    },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                AsyncImage(
                    placeholder = painterResource(id = R.drawable.default_favicon),
                    fallback = painterResource(id = R.drawable.default_favicon),
                    model = uiModel.favicon,
                    contentDescription = null,
                    modifier = Modifier
                        .padding(start = 10.dp)
                        .width(30.dp)
                        .height(30.dp)
                )
                Text(
                    text = uiModel.domain, modifier = Modifier
                        .padding(16.dp)
                        .weight(1f)
                )

                Icon(
                    modifier = Modifier.padding(end = 10.dp),
                    imageVector = if (detailsVisible) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                    contentDescription = null,
                )
            }
            if (detailsVisible) {
                Column(
                    modifier = Modifier.wrapContentWidth(),
                    verticalArrangement = Arrangement.Center,
                ) {
                    Row(
                        modifier = Modifier.wrapContentWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = uiModel.clientIp, modifier = Modifier.padding(10.dp)
                        )
                    }
                    Text(
                        text = uiModel.timestamp, modifier = Modifier.padding(10.dp)
                    )
                }
            }
        }
    }
}
