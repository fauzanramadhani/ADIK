package com.fgr.adik.ui.screen.auth.reset_password

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.fgr.adik.R
import com.fgr.adik.component.button.ButtonPrimary
import com.fgr.adik.component.navbar.NavBarSecondary
import com.fgr.adik.component.text_field.TextFieldPrimary
import com.fgr.adik.component.warning.WarningMedium
import com.fgr.adik.repository.utils.isEmailInvalid
import com.fgr.adik.ui.theme.ADIKTheme

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun ForgotPasswordScreen(
    navHostController: NavHostController
) {
    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusController = LocalFocusManager.current
    var stateEmailText by rememberSaveable {
        mutableStateOf("")
    }
    var stateEmailError by rememberSaveable {
        mutableStateOf(false)
    }
    var stateEmailErrorText by rememberSaveable {
        mutableStateOf("")
    }

    Scaffold(
        topBar = {
            NavBarSecondary(
                title = stringResource(id = R.string.login),
                onBackButtonClick = {
                    navHostController.navigateUp()
                }
            )
        }
    ) { paddingValues ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(24.dp),
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surface)
                .verticalScroll(rememberScrollState())
                .padding(
                    start = 24.dp,
                    end = 24.dp,
                    top = paddingValues.calculateTopPadding() + 24.dp,
                    bottom = 24.dp
                )
        ) {
            Image(
                painter = painterResource(id = R.drawable.forgot_pasword_illustration),
                contentDescription = null,
                modifier = Modifier
                    .width(256.dp)
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = stringResource(id = R.string.screen_forgot_password_title),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface,
                textAlign = TextAlign.Center
            )
            TextFieldPrimary(
                label = stringResource(id = R.string.email),
                value = stateEmailText,
                onValueChange = { value ->
                    if (value.length <= 32) {
                        stateEmailText = value
                        stateEmailError = false
                    } else {
                        stateEmailError = true
                        stateEmailErrorText =
                            context.getString(R.string.supporting_text_error_max_char, "32")
                    }
                },
                error = stateEmailError,
                errorText = stateEmailErrorText,
                modifier = Modifier
                    .fillMaxWidth()
            )
            WarningMedium(
                message = stringResource(id = R.string.screen_forgot_password_warning_text),
            )
            ButtonPrimary(
                enabled = stateEmailText.isNotEmpty(),
                text = stringResource(id = R.string.screen_forgot_password_send_email_reset_password),
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    focusController.clearFocus(true)
                    keyboardController?.hide()
                    when {
                        (stateEmailText.isEmailInvalid()) -> {
                            stateEmailError = true
                            stateEmailErrorText =
                                context.getString(R.string.supporting_text_invalid_email)
                        }

                        else -> {
                            // TODO
                        }
                    }
                }
            )
        }
    }
}

@Preview
@Composable
fun ResetPasswordScreenPreview() {
    ADIKTheme {
        ForgotPasswordScreen(navHostController = rememberNavController())
    }
}