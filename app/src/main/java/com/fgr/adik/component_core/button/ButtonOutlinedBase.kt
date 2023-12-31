package com.fgr.adik.component_core.button

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

@Composable
fun ButtonOutlinedBase(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    contentColor: Color = MaterialTheme.colorScheme.primary,
    borderColor: Color = MaterialTheme.colorScheme.primary,
    shape: Shape = RoundedCornerShape(100.dp),
    paddingValues: PaddingValues = PaddingValues(horizontal = 12.dp),
    onClick: () -> Unit = {},
    content: @Composable RowScope.() -> Unit = {},
) {
    with(MaterialTheme) {
        OutlinedButton(
            modifier = modifier,
            enabled = enabled,
            shape = shape,
            colors = ButtonDefaults.outlinedButtonColors(
                containerColor = colorScheme.surface,
                contentColor = contentColor,
                disabledContainerColor = colorScheme.surface,
                disabledContentColor = colorScheme.outline.copy(0.38f),
            ),
            border = if (enabled) {
                BorderStroke(
                    width = 0.5.dp,
                    color = borderColor,
                )
            } else {
                BorderStroke(
                    width = 0.5.dp,
                    color = colorScheme.outline.copy(0.38f),
                )
            },
            contentPadding = paddingValues,
            onClick = onClick,
            content = content
        )
    }
}