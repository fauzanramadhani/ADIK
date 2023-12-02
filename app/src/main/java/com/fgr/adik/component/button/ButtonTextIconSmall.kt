package com.fgr.adik.component.button

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.fgr.adik.component_core.icon.BaseRoundedShapeIconBox

@Composable
fun ButtonTextIconSmall(
    modifier: Modifier = Modifier,
    icon: @Composable BoxScope.() -> Unit = {},
    title: String = "",
    iconBackgroundColor: Color = MaterialTheme.colorScheme.primary,
    paddingBackground: PaddingValues = PaddingValues(6.dp),
    textColor: Color = MaterialTheme.colorScheme.primary,
    onClick: () -> Unit = {},
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .clip(RoundedCornerShape(4.dp))
            .clickable(onClick = onClick)
            .padding(end = 12.dp)
    ) {
        BaseRoundedShapeIconBox(
            icon = icon,
            iconBackgroundColor = iconBackgroundColor,
            paddingBackground = paddingBackground
        )
        Text(
            text = title,
            style = MaterialTheme.typography.labelLarge,
            color = textColor
        )
    }
}