package io.github.shvmsaini.nextdns.ui.home

import android.content.Intent
import android.provider.Settings
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import io.github.shvmsaini.nextdns.ui.theme.Purple40
import io.github.shvmsaini.nextdns.ui.theme.Purple80

@Composable
fun Status(modifier: Modifier, onClick: () -> Unit) {
    val checked = remember { mutableStateOf(true) }
    Button(onClick = { onClick() }, modifier = modifier) {
        Text("Status")
        Switch(
            checked = checked.value, modifier = modifier.padding(start = 10.dp), onCheckedChange = {
                checked.value = it
            }, colors = SwitchDefaults.colors(
                checkedIconColor = MaterialTheme.colorScheme.primaryContainer,
                uncheckedIconColor = Purple40,
                uncheckedTrackColor = Color.White,
                checkedTrackColor = Purple80,
            )
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomePreview() {
    Home(Modifier.padding(90.dp)) {
    }
}

@Composable
fun Home(modifier: Modifier, onSettingsClick: () -> Unit) {
    Surface(
        modifier = modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    ) {
        Row() {
            Status(Modifier.height(50.dp)) {

            }
            IconButton(onClick = {
                onSettingsClick()
            }) {
                Icon(imageVector = Icons.Filled.Settings, contentDescription = null)
            }
        }
    }
}