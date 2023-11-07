package com.fgr.adik.component.dialog

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fgr.adik.component.button.ButtonPrimary
import com.fgr.adik.ui.theme.ADIKTheme

@Composable
fun DialogAlert(
    modifier: Modifier = Modifier,
    title: String = "",
    message: String = "",
    confirmText: String = "",
    dismissText: String = "",
    confirmButtonColor: ButtonColors = ButtonDefaults.filledTonalButtonColors(
        containerColor = colorScheme.error,
        contentColor = colorScheme.onError,
        disabledContainerColor = colorScheme.surfaceVariant,
        disabledContentColor = colorScheme.onPrimary
    ),
    onConfirm: () -> Unit,
    onDismiss: () -> Unit,
) {
    AlertDialog(
        modifier = modifier,
        containerColor = colorScheme.surface,
        shape = RoundedCornerShape(28.dp),
        onDismissRequest = {
            onDismiss()
        },
        title = {
            Text(
                text = title,
                style = typography.titleMedium,
                color = colorScheme.onSurface
            )
        },
        text = {
            Text(
                text = message,
                style = typography.bodySmall,
                color = colorScheme.onSurface
            )
        },
        confirmButton = {
            ButtonPrimary(
                text = confirmText,
                onClick = {
                    onConfirm()
                },
                colors = confirmButtonColor,
            )
        },
        dismissButton = {
            ButtonPrimary(
                text = dismissText,
                onClick = {
                    onDismiss()
                },
            )
        }
    )
}

@Preview(showSystemUi = true)
@Composable
fun DialogAlertPreview() {
    ADIKTheme {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
        ) {
            var dialogState by rememberSaveable {
                mutableStateOf(false)
            }
            ButtonPrimary(
                text = "Show Dialog"
            ) {
                dialogState = true
            }
            if (dialogState) {
                DialogAlert(
                    title = "Konfirmasi Keluar Akun",
                    message = "Apakah anda yakin ingin keluar dari akun Anda?",
                    confirmText = "Keluar",
                    dismissText = "Batal",
                    onConfirm = {
                        dialogState = false
                    },
                    onDismiss = {
                        dialogState = false
                    }
                )
            }


        }
    }
}