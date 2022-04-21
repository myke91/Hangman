package com.myke.hangman.ui.composables

import androidx.compose.foundation.verticalScroll
import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.myke.hangman.R

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