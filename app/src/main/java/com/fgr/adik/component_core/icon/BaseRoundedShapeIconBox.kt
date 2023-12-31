package com.fgr.adik.component_core.icon

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun BaseRoundedShapeIconBox(
    modifier: Modifier = Modifier,
    iconBackgroundColor: Color = colorScheme.primary,
    paddingBackground: PaddingValues = PaddingValues(6.dp),
    onClick: (() -> Unit)? = null,
    icon: @Composable BoxScope.() -> Unit = {}
) {
    if (onClick != null) {
        Box(
            modifier = modifier
                .clip(RoundedCornerShape(4.dp))
                .background(
                    color = iconBackgroundColor
                )
                .size(28.dp)
                .clickable(onClick = onClick)
                .padding(paddingBackground)
        ) {
            icon()
        }
    } else {
        Box(
            modifier = modifier
                .clip(RoundedCornerShape(4.dp))
                .background(
                    color = iconBackgroundColor
                )
                .size(28.dp)
                .padding(paddingBackground)
        ) {
            icon()
        }
    }

}