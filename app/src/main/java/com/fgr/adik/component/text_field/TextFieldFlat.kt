package com.fgr.adik.component.text_field

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.fgr.adik.component.z9_others.HorizontalDiv
import com.fgr.adik.ui.theme.ADIKTheme

@Composable
fun TextFieldFlat(
    modifier: Modifier = Modifier,
    value: String = "",
    onValueChange: (String) -> Unit = {},
    readOnly: Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    maxLines: Int = Int.MAX_VALUE,
) {
    Column(
        modifier = modifier
    ) {
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier
                .fillMaxWidth(),
            readOnly = readOnly,
            maxLines = maxLines,
            textStyle = typography.bodyMedium,
            keyboardOptions = keyboardOptions,
        )
        HorizontalDiv()
    }
}

@Preview
@Composable
private fun TextFieldFlatPreview() {
    ADIKTheme {
        TextFieldFlat()
    }
}