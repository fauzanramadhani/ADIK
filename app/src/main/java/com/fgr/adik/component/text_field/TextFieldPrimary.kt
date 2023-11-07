package com.fgr.adik.component.text_field

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fgr.adik.component_core.text_field.TextFieldOutlinedBase
import com.fgr.adik.ui.theme.ADIKTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldPrimary(
    modifier: Modifier = Modifier,
    label: String = "",
    value: String = "",
    onValueChange: (String) -> Unit = {},
    enabled: Boolean = true,
    error: Boolean = false,
    errorText: String = "",
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    singleLine: Boolean = false,
    maxLines: Int = 2,
) {
    TextFieldOutlinedBase(
        modifier = modifier,
        label = label,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = MaterialTheme.colorScheme.onSurface,
            unfocusedBorderColor = MaterialTheme.colorScheme.outline,
            focusedBorderColor = MaterialTheme.colorScheme.primary,
            errorBorderColor = MaterialTheme.colorScheme.error,
            errorSupportingTextColor = MaterialTheme.colorScheme.error
        ),
        value = value,
        onValueChange = onValueChange,
        enabled = enabled,
        error = error,
        supportingText = if (error) {
            {
                Text(
                    text = errorText,
                    style = MaterialTheme.typography.labelMedium,
                )
            }
        } else {
            null
        },
        maxLines = maxLines,
        keyboardOptions = keyboardOptions,
        singleLine = singleLine,
    )
}

@Preview
@Composable
fun TextFieldPrimaryPreview() {
    val value = "Lorem ipsum wakwaw"
    ADIKTheme {
        Column(
            verticalArrangement = Arrangement.spacedBy(24.dp),
            modifier = Modifier
                .padding(24.dp)
        ) {
            TextFieldPrimary(
                value = value
            )
            TextFieldPrimary(
                value = value,
                error = true,
                errorText = "Email anda tidak valid"
            )
            TextFieldPrimary(
                value = value,
                enabled = false
            )
        }
    }
}