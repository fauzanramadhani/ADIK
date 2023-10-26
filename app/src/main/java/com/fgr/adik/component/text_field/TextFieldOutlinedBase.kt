package com.fgr.adik.component.text_field

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldOutlinedBase(
    modifier: Modifier = Modifier,
    label: String = "",
    shape: Shape = RoundedCornerShape(4.dp),
    colors: TextFieldColors = TextFieldDefaults.outlinedTextFieldColors(),
    value: String = "",
    onValueChange: (String) -> Unit = {},
    enabled: Boolean = true,
    error: Boolean = false,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    supportingText: @Composable (() -> Unit)? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    singleLine: Boolean = false,
    maxLines: Int = Int.MAX_VALUE,
) {
    OutlinedTextField(
        modifier = modifier,
        label = {
            Text(
                text = label,
                style = typography.titleMedium,
                color = colorScheme.secondary
            )
        },
        shape = shape,
        colors = colors,
        value = value,
        onValueChange = onValueChange,
        enabled = enabled,
        isError = error,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        supportingText = supportingText,
        visualTransformation = visualTransformation,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        singleLine = singleLine,
        maxLines = maxLines
    )
}