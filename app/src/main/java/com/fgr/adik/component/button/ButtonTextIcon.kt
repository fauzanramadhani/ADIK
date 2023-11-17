package com.fgr.adik.component.button

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.fgr.adik.component_core.icon.BaseRoundedShapeIconBox
import com.fgr.adik.component_core.item.BaseItem

@Composable
fun ButtonTextIcon(
    modifier: Modifier = Modifier,
    icon: @Composable BoxScope.() -> Unit = {},
    title: String = "",
    body: String? = null,
    iconBackgroundColor: Color = colorScheme.primary,
    textColor: Color = colorScheme.primary,
    arrowColor: Color = colorScheme.secondary,
    onContentClicked: () -> Unit = {},
) {
    BaseItem(
        modifier = modifier,
        arrowColor = arrowColor,
        onContentClicked = onContentClicked,
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
        ) {
            BaseRoundedShapeIconBox(
                icon = icon,
                iconBackgroundColor = iconBackgroundColor
            )
            Text(
                text = title,
                style = typography.labelLarge,
                color = textColor
            )
            if (body != null) {
                Text(
                    text = body,
                    style = typography.bodyMedium,
                    color = colorScheme.onSurface
                )
            }
        }
    }
}