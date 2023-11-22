package com.fgr.adik.component.navbar

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fgr.adik.R
import com.fgr.adik.component.button.ButtonHelp
import com.fgr.adik.component_core.navbar.NavBarBase
import com.fgr.adik.ui.theme.ADIKTheme

@Composable
fun NavBarPrimary(
    onSupportButtonClicked: () -> Unit = {}
) {
    NavBarBase(
        paddingValues = PaddingValues(horizontal = 24.dp),
    ) {
        Image(
            painter = painterResource(id = R.drawable.icon_adik),
            contentDescription = "",
        )
        ButtonHelp(
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