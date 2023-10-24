package com.fgr.adik.component.z9_others

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun HorizontalDiv(
    modifier: Modifier = Modifier,
    thickness: Dp = 0.2.dp,
    color: Color = MaterialTheme.colorScheme.secondary
) {
    Divider(
        modifier = modifier
            .fillMaxWidth(),
        thickness = thickness,
        color = color
    )
}