package com.fgr.adik.component.warning

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fgr.adik.R
import com.fgr.adik.component_core.warning.BaseWarning
import com.fgr.adik.ui.theme.ADIKTheme

@Composable
fun WarningHigh(
    modifier: Modifier = Modifier,
    message: String = "",
    actionButtonText: String = "",
    actionButtonEnabled: Boolean = false,
    onClickActionButton: (() -> Unit)? = null,
) {
    Box(
        modifier = modifier
    ) {
        BaseWarning(
            containerColor = colorScheme.errorContainer
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_warning),
                contentDescription = null,
                tint = colorScheme.error,
                modifier = Modifier
                    .weight(0.2f)
            )
            Text(
                text = message,
                style = MaterialTheme.typography.bodySmall,
                color = colorScheme.onErrorContainer,
                modifier = Modifier
                    .weight(1f)
            )
            if (actionButtonEnabled && onClickActionButton != null) {
                Text(
                    text = actionButtonText,
                    style = MaterialTheme.typography.labelMedium,
                    color = colorScheme.onSurface,
                    modifier = Modifier
                        .weight(0.4f)
                        .clickable(onClick = onClickActionButton)
                )
            }
        }
    }
}

@Preview
@Composable
fun WarningHighPreview() {
    ADIKTheme {
        WarningHigh(
            message = "This is high warning",
            modifier = Modifier
                .padding(24.dp)
        )
    }
}