package com.fgr.adik.component.navbar

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.fgr.adik.R
import com.fgr.adik.component.button.ButtonHelp
import com.fgr.adik.ui.theme.ADIKTheme

@Composable
fun NavBarPrimary(
    supportButtonEnabled: Boolean = true,
    onSupportButtonClicked: () -> Unit = {}
) {
    BaseNavBar {
        Image(
            painter = painterResource(id = R.drawable.icon_adik),
            contentDescription = "",
        )
        ButtonHelp(
            enabled = supportButtonEnabled,
            onClick = onSupportButtonClicked
        )
    }
}

@Preview
@Composable
fun NavBarPrimaryPreview() {
    ADIKTheme {
        NavBarPrimary()
    }
}