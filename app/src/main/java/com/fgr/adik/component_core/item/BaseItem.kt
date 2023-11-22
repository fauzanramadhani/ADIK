package com.fgr.adik.component_core.item

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fgr.adik.ui.theme.ADIKTheme

@Composable
fun BaseItem(
    modifier: Modifier = Modifier,
    arrowColor: Color = colorScheme.secondary,
    onContentClicked: () -> Unit = {},
    content: @Composable BoxScope.() -> Unit = {},
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(24.dp),
        modifier = modifier
            .clickable(onClick = onContentClicked)
            .background(color = colorScheme.surface)
            .fillMaxWidth()
            .padding(24.dp)
    ) {
        Box(
            modifier = Modifier.weight(1f),
            content = content
        )
        Icon(
            imageVector = Icons.Default.ArrowForward,
            contentDescription = null,
            tint = arrowColor,
            modifier = Modifier
                .weight(0.1f)
        )
    }
}

@Preview
@Composable
fun BaseItemPreview() {
    ADIKTheme {
        BaseItem {
            Text(text = "Test")
        }
    }
}