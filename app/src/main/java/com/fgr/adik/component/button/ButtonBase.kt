package com.fgr.adik.component.button

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

@Composable
fun ButtonBase(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    shapes: Shape = RoundedCornerShape(100.dp),
    contentPadding: PaddingValues = PaddingValues(horizontal = 12.dp),
    colors: ButtonColors = ButtonDefaults.outlinedButtonColors(),
    border: BorderStroke = ButtonDefaults.outlinedButtonBorder,
    onClick: () -> Unit = {},
    content: @Composable RowScope.() -> Unit,
) {
    OutlinedButton(
        modifier = modifier,
        enabled = enabled,
        shape = shapes,
        contentPadding = contentPadding,
        colors = colors,
        border = border,
        onClick = onClick,
        content = content
    )
}