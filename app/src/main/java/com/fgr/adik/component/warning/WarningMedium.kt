package com.fgr.adik.component.warning

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
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
fun WarningMedium(
    modifier: Modifier = Modifier,
    message: String = "",
) {
    Box(
        modifier = modifier
    ) {
        BaseWarning(
            containerColor = colorScheme.tertiaryContainer
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_warning),
                contentDescription = null,
                tint = colorScheme.tertiary
            )
            Text(
                text = message,
                style = typography.bodySmall,
                color = colorScheme.onTertiaryContainer
            )
        }
    }
}

@Preview
@Composable
fun WarningMediumPreview() {
    ADIKTheme {
        WarningMedium(
            message = "This is medium warning",
            modifier = Modifier
                .padding(24.dp)
        )
    }
}