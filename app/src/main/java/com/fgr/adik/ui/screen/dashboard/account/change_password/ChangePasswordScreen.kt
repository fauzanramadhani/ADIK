package com.fgr.adik.ui.screen.dashboard.account.change_password

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.fgr.adik.R
import com.fgr.adik.component.button.ButtonPrimary
import com.fgr.adik.component.navbar.NavBarSecondary
import com.fgr.adik.component.text_field.TextFieldPassword
import com.fgr.adik.component.z9_others.DialogLoading
import com.fgr.adik.ui.theme.ADIKTheme
import com.fgr.adik.utils.Toast

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChangePasswordScreen(
    navHostController: NavHostController,
    changePasswordViewModel: ChangePasswordViewModel = hiltViewModel(),
) {
    val context = LocalContext.current
    // Old Password
    var stateOldPasswordText by rememberSaveable {
        mutableStateOf("")
    }
    var stateOldPasswordError by rememberSaveable {
        mutableStateOf(false)
    }
    var stateOldPasswordErrorText by rememberSaveable {
        mutableStateOf("")
    }
    var stateOldPasswordVisibility by rememberSaveable {
        mutableStateOf(false)
    }
    // New Password
    var stateNewPasswordText by rememberSaveable {
        mutableStateOf("")
    }
    var stateNewPasswordError by rememberSaveable {
        mutableStateOf(false)
    }
    var stateNewPasswordErrorText by rememberSaveable {
        mutableStateOf("")
    }
    var stateNewPasswordVisibility by rememberSaveable {
        mutableStateOf(false)
    }
    // New Password Confirmation
    var stateNewPasswordConfirmationText by rememberSaveable {
        mutableStateOf("")
    }
    var stateNewPasswordConfirmationError by rememberSaveable {
        mutableStateOf(false)
    }
    var stateNewPasswordConfirmationErrorText by rememberSaveable {
        mutableStateOf("")
    }
    var stateNewPasswordConfirmationVisibility by rememberSaveable {
        mutableStateOf(false)
    }
    var stateButtonEnabled by rememberSaveable {
        mutableStateOf(true)
    }
    var loadingState by rememberSaveable {
        mutableStateOf(false)
    }
    if (loadingState) {
        DialogLoading()
    }

    Scaffold(
        topBar = {
            NavBarSecondary(
                title = stringResource(id = R.string.screen_change_password_nav_title),
                onBackButtonClick = {
                    navHostController.popBackStack()
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
                painter = painterResource(id = R.drawable.change_password_illustration),
                contentDescription = null
            )
            Spacer(modifier = Modifier.padding(top = 24.dp))
            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                Text(
                    text = stringResource(id = R.string.screen_change_password_old_password),
                    style = typography.labelLarge,
                )
                TextFieldPassword(
                    modifier = Modifier
                        .fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Next,
                        keyboardType = KeyboardType.Password,
                        autoCorrect = false
                    ),
                    label = stringResource(id = R.string.screen_change_password_old_password),
                    error = stateOldPasswordError,
                    errorText = stateOldPasswordErrorText,
                    value = stateOldPasswordText,
                    onValueChange = { value ->
                        if (value.length <= 32) {
                            stateOldPasswordText = value
                            stateOldPasswordError = false
                        } else {
                            stateOldPasswordError = true
                            stateOldPasswordErrorText =
                                context.getString(R.string.supporting_text_error_max_char, "32")
                        }
                    },
                    visibility = stateOldPasswordVisibility,
                    onVisibilityChange = {
                        stateOldPasswordVisibility = !stateOldPasswordVisibility
                    }
                )
            }
            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                Text(
                    text = stringResource(id = R.string.screen_change_password_new_password),
                    style = typography.labelLarge,
                )
                TextFieldPassword(
                    modifier = Modifier
                        .fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Next,
                        keyboardType = KeyboardType.Password,
                        autoCorrect = false
                    ),
                    label = stringResource(id = R.string.screen_change_password_new_password),
                    error = stateNewPasswordError,
                    errorText = stateNewPasswordErrorText,
                    value = stateNewPasswordText,
                    onValueChange = { value ->
                        if (value.length <= 32) {
                            stateNewPasswordText = value
                            stateNewPasswordError = false
                        } else {
                            stateNewPasswordError = true
                            stateNewPasswordErrorText =
                                context.getString(R.string.supporting_text_error_max_char, "32")
                        }
                    },
                    visibility = stateNewPasswordVisibility,
                    onVisibilityChange = {
                        stateNewPasswordVisibility = !stateNewPasswordVisibility
                    }
                )
            }
            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                Text(
                    text = stringResource(id = R.string.screen_change_password_new_password_confirmation),
                    style = typography.labelLarge,
                )
                TextFieldPassword(
                    modifier = Modifier
                        .fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Done,
                        keyboardType = KeyboardType.Password,
                        autoCorrect = false
                    ),
                    label = stringResource(id = R.string.screen_change_password_new_password_confirmation),
                    error = stateNewPasswordConfirmationError,
                    errorText = stateNewPasswordConfirmationErrorText,
                    value = stateNewPasswordConfirmationText,
                    onValueChange = { value ->
                        if (value.length <= 32) {
                            stateNewPasswordConfirmationText = value
                            stateNewPasswordConfirmationError = false
                        } else {
                            stateNewPasswordConfirmationError = true
                            stateNewPasswordConfirmationErrorText =
                                context.getString(R.string.supporting_text_error_max_char, "32")
                        }
                    },
                    visibility = stateNewPasswordConfirmationVisibility,
                    onVisibilityChange = {
                        stateNewPasswordConfirmationVisibility =
                            !stateNewPasswordConfirmationVisibility
                    }
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            ButtonPrimary(
                text = stringResource(id = R.string.screen_change_password_nav_title),
                enabled = stateButtonEnabled && stateOldPasswordText.isNotEmpty() &&
                        stateNewPasswordText.isNotEmpty() && stateNewPasswordConfirmationText.isNotEmpty(),
                modifier = Modifier
                    .fillMaxWidth(),
            ) {
                when {
                    (stateNewPasswordText.length < 12) -> {
                        stateNewPasswordError = true
                        stateNewPasswordErrorText =
                            context.getString(R.string.supporting_text_less_than_12_password)
                    }

                    stateNewPasswordText != stateNewPasswordConfirmationText -> {
                        stateNewPasswordConfirmationError = true
                        stateNewPasswordConfirmationErrorText =
                            context.getString(R.string.supporting_text_invalid_password_confirmation)
                    } else -> {
                    loadingState = true
                        changePasswordViewModel.changePassword(
                            stateOldPasswordText,
                            stateNewPasswordText
                        ) { success, message ->
                            loadingState = false
                            Toast(context, message).long()
                            if (success) navHostController.popBackStack()
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun ChangePasswordScreenPreview() {
    ADIKTheme {
        ChangePasswordScreen(
            navHostController = rememberNavController()
        )
    }
}