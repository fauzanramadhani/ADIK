package com.fgr.adik.component.button

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fgr.adik.R
import com.fgr.adik.component_core.button.ButtonOutlinedBase
import com.fgr.adik.ui.theme.ADIKTheme

@Composable
fun ButtonHelp(
    enabled: Boolean = true,
    onClick: () -> Unit = {},
) {
    ButtonOutlinedBase(
        modifier = Modifier
            .height(36.dp),
        enabled = enabled,
        onClick = onClick
    ) {
        Icon(
            painter = painterResource(
                id = R.drawable.icon_support
            ),
            contentDescription = ""
        )
        Spacer(modifier = Modifier.padding(horizontal = 4.dp))
        Text(text = stringResource(id = R.string.button_help))
    }
}

@Preview
@Composable
fun ButtonHelpPreview() {
    ADIKTheme {
        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.padding(24.dp)
        ) {
            ButtonHelp()
            ButtonHelp(enabled = false)
        }
    }
}