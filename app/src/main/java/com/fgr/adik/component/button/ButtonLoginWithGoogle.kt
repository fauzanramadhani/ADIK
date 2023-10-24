package com.fgr.adik.component.button

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fgr.adik.R
import com.fgr.adik.ui.theme.ADIKTheme

@Composable
fun ButtonLoginWithGoogle(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    onClick: () -> Unit = {}
) {
    with(MaterialTheme) {
        ButtonOutlinedBase(
            modifier = modifier,
            enabled = enabled,
            contentColor = colorScheme.secondary,
            borderColor = colorScheme.secondary,
            onClick = onClick
        ) {
            Image(
                painter = painterResource(id = R.drawable.icon_google),
                colorFilter = if (!enabled) {
                    ColorFilter.tint(
                        color = colorScheme.secondary.copy(0.38f)
                    )
                } else {
                    null
                },
                contentDescription = null,
            )
            Spacer(
                modifier = Modifier
                    .padding(horizontal = 4.dp)
            )
            Text(
                text = stringResource(id = R.string.button_sign_in_with_google),
                style = typography.labelLarge,
            )
        }
    }
}

@Preview
@Composable
fun ButtonSignInWithGooglePreview() {
    ADIKTheme {
        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier
                .padding(24.dp)
        ) {
            ButtonLoginWithGoogle(
                enabled = true,
                modifier = Modifier
                    .fillMaxWidth()
            )
            ButtonLoginWithGoogle(
                enabled = false,
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
    }
}