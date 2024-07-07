package io.github.shvmsaini.nextdns.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputTextDialog(
    title: String,
    label: String,
    showDialog: Boolean,
    onDismiss: () -> Unit,
    onConfirm: (String) -> Unit
) {
    var text by remember { mutableStateOf("") }
    if (showDialog){
        AlertDialog(
            onDismissRequest = onDismiss,
            title = { Text(title) },
//            containerColor = if (isSystemInDarkTheme()) Purple40 else PurpleGrey80,
            text = {
                Column {
                    TextField(
                        value = text,
                        onValueChange = { text = it },
                        label = { Text(label) }
                    )
                }
            },
            confirmButton = {
                Button(onClick = { onConfirm(text) }) {
                    Text("Confirm")
                }
            },
            dismissButton = {
                TextButton(onClick = onDismiss) {
                    Text("Cancel")
                }
            })
    }
}