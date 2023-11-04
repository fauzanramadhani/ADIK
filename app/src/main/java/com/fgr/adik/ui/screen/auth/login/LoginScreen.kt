package com.fgr.adik.ui.screen.auth.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
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
import com.fgr.adik.component.text_field.TextFieldPassword
import com.fgr.adik.component.text_field.TextFieldPrimary
import com.fgr.adik.navigation.NavRoute
import com.fgr.adik.repository.utils.isEmailInvalid
import com.fgr.adik.repository.utils.navigateToTop
import com.fgr.adik.ui.theme.ADIKTheme

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun LoginScreen(
    navHostController: NavHostController,
) {
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
    var statePasswordText by rememberSaveable {
        mutableStateOf("")
    }
    var statePasswordVisibility by rememberSaveable {
        mutableStateOf(false)
    }
    var statePasswordError by rememberSaveable {
        mutableStateOf(false)
    }
    var statePasswordErrorText by rememberSaveable {
        mutableStateOf("")
    }
    var statePasswordConfirmationText by rememberSaveable {
        mutableStateOf("")
    }
    var statePasswordConfirmationVisibility by rememberSaveable {
        mutableStateOf(false)
    }
    var statePasswordConfirmationError by rememberSaveable {
        mutableStateOf(false)
    }
    var statePasswordConfirmationErrorText by rememberSaveable {
        mutableStateOf("")
    }
    var loginButtonEnabledState by rememberSaveable {
        mutableStateOf(true)
    }
    val context = LocalContext.current

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
                .background(colorScheme.surface)
                .verticalScroll(rememberScrollState())
                .padding(
                    start = 24.dp,
                    end = 24.dp,
                    top = paddingValues.calculateTopPadding() + 24.dp,
                    bottom = 24.dp
                )
        ) {
            Image(
                painter = painterResource(id = R.drawable.login_illustration),
                contentDescription = null,
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = stringResource(id = R.string.screen_login_title),
                style = typography.bodyLarge,
                color = colorScheme.onSurface,
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
            TextFieldPassword(
                label = stringResource(id = R.string.password),
                value = statePasswordText,
                onValueChange = { value ->
                    if (value.length <= 32) {
                        statePasswordText = value
                        statePasswordError = false
                    } else {
                        statePasswordError = true
                        statePasswordErrorText =
                            context.getString(R.string.supporting_text_error_max_char, "32")
                    }
                },
                error = statePasswordError,
                errorText = statePasswordErrorText,
                visibility = statePasswordVisibility,
                onVisibilityChange = {
                    statePasswordVisibility = !statePasswordVisibility
                },
                modifier = Modifier
                    .fillMaxWidth()
            )
            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp),

                    ) {
                    Text(
                        text = stringResource(id = R.string.screen_login_not_have_an_account),
                        style = typography.bodyMedium
                    )
                    Text(
                        text = stringResource(id = R.string.register),
                        style = typography.labelLarge,
                        modifier = Modifier
                            .clickable {
//                                keyboardController?.hide()
//                                focusController.clearFocus(true)
                                navHostController.navigateToTop(
                                    destination = NavRoute.EmailVerificationScreen
                                )
                            }
                    )
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    modifier = Modifier
                ) {
                    Text(
                        text = stringResource(id = R.string.screen_login_forgot_password),
                        style = typography.bodyMedium
                    )
                    Text(
                        text = stringResource(id = R.string.screen_login_reset_password),
                        style = typography.labelLarge,
                        modifier = Modifier
                            .clickable {
                                navHostController.navigateToTop(NavRoute.ForgotPasswordScreen)
                            }
                    )
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Spacer(modifier = Modifier.weight(0.5f))
                ButtonPrimary(
                    enabled = loginButtonEnabledState && stateEmailText.isNotEmpty() && statePasswordText.isNotEmpty(),
                    text = stringResource(id = R.string.register),
                    modifier = Modifier.weight(0.5f),
                    onClick = {
                        keyboardController?.hide()
                        focusController.clearFocus(true)
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
}

@Preview
@Composable
fun LoginScreenPreview() {
    ADIKTheme {
        LoginScreen(navHostController = rememberNavController())
    }
}