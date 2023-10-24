package com.fgr.adik.component.button

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fgr.adik.ui.theme.ADIKTheme

@Composable
fun ButtonPrimary(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    text: String = "",
    colors: ButtonColors = ButtonDefaults.filledTonalButtonColors(
        containerColor = MaterialTheme.colorScheme.primary,
        contentColor = MaterialTheme.colorScheme.onPrimary,
        disabledContainerColor = MaterialTheme.colorScheme.surfaceVariant,
        disabledContentColor = MaterialTheme.colorScheme.onPrimary
    ),
    onClick: () -> Unit = {},
) {
    ButtonPrimaryBase(
        modifier = modifier,
        colors = colors,
        enabled = enabled,
        onClick = onClick
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelLarge,
        )
    }
}

@Preview
@Composable
fun ButtonPrimaryPreview() {
    ADIKTheme {
        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier
                .padding(24.dp)
        ) {
            ButtonPrimary(
                modifier = Modifier.fillMaxWidth(),
                enabled = true,
                text = "Enabled"
            )
            ButtonPrimary(
                modifier = Modifier.fillMaxWidth(),
                enabled = false,
                text = "Disabled"
            )
        }
    }
}