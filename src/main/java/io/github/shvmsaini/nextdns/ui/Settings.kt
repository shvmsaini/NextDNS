package io.github.shvmsaini.nextdns.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.shvmsaini.nextdns.utils.Theme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Settings(
    theme: Theme,
    onNavigateClick: () -> Unit,
    onThemeSelected: (Theme) -> Unit,
) {
    val themeDialog = remember { mutableStateOf(false) }
    if (themeDialog.value) {
        ThemeSelectionDialog(selectedTheme = theme, onThemeSelected = {
            onThemeSelected(it)
        }) {
            themeDialog.value = false
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = { onNavigateClick() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            tint = MaterialTheme.colorScheme.primary,
                            contentDescription = null
                        )
                    }
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text(
                        modifier = Modifier.padding(start = 16.dp),
                        text = "Personalization"
                    )
                }
            )

        }
    ) { innerPadding ->
        val modifier = Modifier.padding(innerPadding)
        Column(
            modifier = modifier
        ) {
            SettingCard(
                modifier = Modifier
                    .padding(0.dp)
                    .verticalScroll(rememberScrollState()),
                title = "Theme",
                subTitle = theme.name,
            ) {
                // Do something
                themeDialog.value = true
            }
        }
    }
}


@Composable
@Preview(showBackground = true, showSystemUi = true)
fun SettingsPreview(
) {
    Settings(Theme.SYSTEM, onNavigateClick = {}, onThemeSelected = {})
}

@Composable
fun ThemeSelectionDialog(
    selectedTheme: Theme,
    onThemeSelected: (Theme) -> Unit,
    onDismiss: () -> Unit
) {
    var currSelectedTheme by remember { mutableStateOf(selectedTheme) }
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Choose Theme") },
        text = {
            Column {
                ThemeOption(
                    text = "Light",
                    selected = currSelectedTheme == Theme.LIGHT,
                    onClick = { currSelectedTheme = Theme.LIGHT }
                )
                ThemeOption(
                    text = "Dark",
                    selected = currSelectedTheme == Theme.DARK,
                    onClick = { currSelectedTheme = Theme.DARK }
                )
                ThemeOption(
                    text = "System",
                    selected = currSelectedTheme == Theme.SYSTEM,
                    onClick = { currSelectedTheme = Theme.SYSTEM }
                )
            }
        },
        confirmButton = {
            Button(onClick = {
                onThemeSelected(currSelectedTheme)
                onDismiss()
            }) {
                Text("OK")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}

@Composable
fun ThemeOption(
    text: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = selected,
            onClick = onClick
        )
        Text(
            text = text,
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}