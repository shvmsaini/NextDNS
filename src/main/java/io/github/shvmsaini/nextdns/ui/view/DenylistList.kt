package io.github.shvmsaini.nextdns.ui.view

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import io.github.shvmsaini.nextdns.R
import io.github.shvmsaini.nextdns.TAG
import io.github.shvmsaini.nextdns.domain.model.DenylistUiModel

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DenylistPreview() {
    DenylistList(
        isAllowlist = false,
        modifier = Modifier,
        list = arrayListOf<DenylistUiModel>().apply {
            repeat(2) {
                this.add(
                    DenylistUiModel(
                        id = "e4fffffffffffffffffffffffffffffffffffffffffffffffff44",
                        active = false,
                        favicon = "",
                    )
                )
            }
        }
    )
}

@Composable
fun DenylistList(
    isAllowlist: Boolean,
    modifier: Modifier,
    list: List<DenylistUiModel>,
) {
    Surface(
        color = MaterialTheme.colorScheme.background,
        modifier = modifier
            .fillMaxSize(),
    ) {
        LazyColumn(
            contentPadding = PaddingValues(bottom = 64.dp)
        ) {
            items(count = list.size, itemContent = { index ->
                DenylistItem(isAllowlist, list[index])
            })
        }
    }
}

@Composable
fun DenylistItem(
    isAllowlist: Boolean,
    uiModel: DenylistUiModel,
) {
    Row(
        modifier = Modifier
            .height(IntrinsicSize.Min)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .width(4.dp)
                .background(
                    color =
                    if (uiModel.active) (if (isAllowlist) Color.Green else Color.Red) else Color.Gray
                )
        )
        AsyncImage(
            placeholder = painterResource(id = R.drawable.default_favicon),
            fallback = painterResource(id = R.drawable.default_favicon),
            model = uiModel.favicon,
            contentDescription = null,
            modifier = Modifier
                .padding(10.dp)
                .width(30.dp)
                .height(30.dp)
        )
        Text(
            text = "*." + uiModel.id,
            modifier = Modifier
                .padding(10.dp)
                .weight(1f),
        )
        Switch(
            checked = uiModel.active,
            onCheckedChange = {
                Log.d(TAG, "DenylistItem: Switched")
            },
            modifier = Modifier
                .wrapContentWidth(align = Alignment.End)
                .padding(end = 0.dp)
        )
        IconButton(onClick = {
            Log.d(TAG, "DenylistItem: Cross")
        }) {
            Icon(
                imageVector = Icons.Default.Clear, contentDescription = null,
                modifier = Modifier
            )
        }
    }
}