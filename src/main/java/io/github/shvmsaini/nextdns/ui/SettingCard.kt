package io.github.shvmsaini.nextdns.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SettingCard(
    modifier: Modifier,
    title: String,
    subTitle: String,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
            .clickable {
                onClick()
            }
    ) {
        Text(
            text = title,
            modifier = modifier.padding(10.dp),
            style = MaterialTheme.typography.titleMedium
        )
        Text(
            text = subTitle,
            modifier = modifier.padding(start = 10.dp, bottom = 10.dp, top = 0.dp),
            style = MaterialTheme.typography.bodySmall
        )
    }
}