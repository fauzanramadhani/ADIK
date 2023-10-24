package com.fgr.adik.component.button

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ButtonSecondary(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    text: String = "",
    onClick: () -> Unit = {}
) {
    ButtonOutlinedBase(
        modifier = modifier,
        enabled = enabled,
        onClick = onClick,
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelLarge,
        )
    }
}