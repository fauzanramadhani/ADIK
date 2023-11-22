package com.fgr.adik.component.z9_others

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.fgr.adik.R
import com.fgr.adik.component.warning.WarningHigh

@Composable
fun ErrorMessageAndAction(
    modifier: Modifier = Modifier,
    message: String = "",
    actionButtonText: String = "",
    actionButtonEnabled: Boolean = true,
    onClickActionButton: () -> Unit = {}
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Text(text = stringResource(id = R.string.error_message_and_action_title))
        Image(
            painter = painterResource(id = R.drawable.error_illustration),
            contentDescription = null,
            modifier = Modifier
                .size(280.dp)
        )
        WarningHigh(
            modifier = Modifier
                .padding(horizontal = 24.dp),
            message = message,
            actionButtonText = actionButtonText,
            actionButtonEnabled = actionButtonEnabled,
            onClickActionButton = onClickActionButton
        )
    }
}