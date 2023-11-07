package com.fgr.adik.component.navbar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fgr.adik.component.button.ButtonHelp
import com.fgr.adik.component_core.navbar.NavBarBase
import com.fgr.adik.ui.theme.ADIKTheme

@Composable
fun NavBarSecondary(
    title: String = "",
    onBackButtonClick: () -> Unit = {},
    supportButtonShow: Boolean = true,
    onSupportButtonClick: () -> Unit = {}
) {
    NavBarBase(
        paddingValues = PaddingValues(start = 12.dp, end = 24.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onBackButtonClick) {
                Icon(
                    modifier = Modifier
                        .size(48.dp),
                    imageVector = Icons.Default.KeyboardArrowLeft,
                    tint = MaterialTheme.colorScheme.secondary,
                    contentDescription = "",
                )
            }
            Text(
                text = title,
                style = MaterialTheme.typography.titleSmall,
            )
        }
        if (supportButtonShow) {
            ButtonHelp(
                onClick = onSupportButtonClick
            )
        }
    }
}

@Preview
@Composable
fun NavBarSecondaryPreview() {
    ADIKTheme {
        NavBarSecondary("Masuk")
    }
}