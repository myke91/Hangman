package com.myke.hangman.ui.composables

import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable

@Composable
fun SimpleAlertDialog(
    show: Boolean,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
    title: String,
    content: String,
    confirmText: String,
    dismissText: String
) {
    if (show) {
        AlertDialog(
            onDismissRequest = { },
            confirmButton = {
                TextButton(onClick = onConfirm)
                { Text(text = confirmText) }
            },
            dismissButton = {
                TextButton(onClick = onDismiss)
                { Text(text = dismissText) }
            },
            title = { Text(text = title) },
            text = { Text(text = content) }
        )
    }
}