package com.fgr.adik.component.text_field

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fgr.adik.R
import com.fgr.adik.component_core.text_field.TextFieldOutlinedBase
import com.fgr.adik.ui.theme.ADIKTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldPassword(
    modifier: Modifier = Modifier,
    label: String = "",
    value: String = "",
    onValueChange: (String) -> Unit = {},
    enabled: Boolean = true,
    error: Boolean = false,
    errorText: String = "",
    visibility: Boolean = false,
    onVisibilityChange: () -> Unit = {}
) {
    TextFieldOutlinedBase(
        modifier = modifier,
        label = label,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = colorScheme.onSurface,
            unfocusedBorderColor = colorScheme.outline,
            focusedBorderColor = colorScheme.primary,
            errorBorderColor = colorScheme.error,
            errorSupportingTextColor = colorScheme.error
        ),
        value = value,
        onValueChange = onValueChange,
        enabled = enabled,
        error = error,
        trailingIcon = {
            IconButton(
                onClick = onVisibilityChange
            ) {
                Icon(
                    painter = painterResource(
                        id = if (visibility) {
                            R.drawable.ic_visibility
                        } else {
                            R.drawable.ic_visibility_off
                        }
                    ),
                    tint = colorScheme.secondary,
                    contentDescription = null,
                )
            }
        },
        supportingText = if (error) {
            {
                Text(
                    text = errorText,
                    style = typography.labelMedium,
                )
            }
        } else {
            null
        },
        visualTransformation = if (visibility) {
            VisualTransformation.None
        } else {
            PasswordVisualTransformation()
        },
        maxLines = 2
    )
}

@Preview
@Composable
fun TextFieldPasswordPreview() {
    val value = "Lorem ipsum wakwaw"
    ADIKTheme {
        Column(
            verticalArrangement = Arrangement.spacedBy(24.dp),
            modifier = Modifier
                .padding(24.dp)
        ) {
            TextFieldPassword(
                value = value
            )
            TextFieldPassword(
                value = value,
                error = true,
                errorText = "Password anda kurang dari 8 karakter"
            )
            TextFieldPassword(
                value = value,
                enabled = false
            )
        }
    }
}