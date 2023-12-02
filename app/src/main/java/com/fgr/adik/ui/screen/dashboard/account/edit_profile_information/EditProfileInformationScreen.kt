package com.fgr.adik.ui.screen.dashboard.account.edit_profile_information

import androidx.activity.compose.BackHandler
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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.fgr.adik.R
import com.fgr.adik.component.button.ButtonPrimary
import com.fgr.adik.component.dialog.DialogAlert
import com.fgr.adik.component.navbar.NavBarSecondary
import com.fgr.adik.component.text_field.TextFieldPrimary
import com.fgr.adik.component.z9_others.DialogLoading
import com.fgr.adik.navigation.NavRoute
import com.fgr.adik.ui.theme.ADIKTheme
import com.fgr.adik.utils.Toast
import com.fgr.adik.utils.UiState
import com.fgr.adik.utils.isPhoneNumberInvalid
import com.fgr.adik.utils.navigateTo
import com.fgr.adik.utils.navigateToTop

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun EditProfileInformationScreen(
    navHostController: NavHostController,
    editInformationScreenViewModel: EditInformationScreenViewModel = hiltViewModel(),
) {
    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusController = LocalFocusManager.current

    var stateNameText by rememberSaveable {
        mutableStateOf("")
    }
    var stateNameError by rememberSaveable {
        mutableStateOf(false)
    }
    var stateNameErrorText by rememberSaveable {
        mutableStateOf("")
    }
    var stateWhatsAppNumberText by rememberSaveable {
        mutableStateOf("")
    }
    var stateWhatsAppNumberError by rememberSaveable {
        mutableStateOf(false)
    }
    var stateWhatsAppNumberErrorText by rememberSaveable {
        mutableStateOf("")
    }
    var stateAddressText by rememberSaveable {
        mutableStateOf("")
    }
    var stateAddressError by rememberSaveable {
        mutableStateOf(false)
    }
    var stateAddressErrorText by rememberSaveable {
        mutableStateOf("")
    }
    var hasProfile by rememberSaveable {
        mutableStateOf(false)
    }
    var dialogState by rememberSaveable {
        mutableStateOf(false)
    }
    var loadingState by rememberSaveable {
        mutableStateOf(false)
    }

    if (loadingState) {
        DialogLoading()
    }

    if (dialogState) {
        DialogAlert(
            title = stringResource(id = R.string.screen_email_title_dialog_alert_logout),
            message = stringResource(id = R.string.screen_email_message_dialog_alert_logout),
            confirmText = stringResource(id = R.string.out),
            dismissText = stringResource(id = R.string.cancel),
            onConfirm = {
                loadingState = true
                editInformationScreenViewModel.logout {
                    loadingState = false
                    dialogState = false
                    navHostController.navigateToTop(NavRoute.OnBoardingScreen)
                }
            },
            onDismiss = {
                dialogState = false
            }
        )
    }


    when (
        val profileDataState =
        editInformationScreenViewModel.getProfileState.collectAsStateWithLifecycle().value
    ) {
        UiState.Empty -> {
            stateNameText = ""
            stateAddressText = ""
            stateWhatsAppNumberText = ""
            loadingState = false
            hasProfile = false
        }
        is UiState.Error -> {
            loadingState = false
            Toast(context, profileDataState.errorMessage).long()
        }
        UiState.Loading -> {
            loadingState = true
        }
        is UiState.Success -> {
            with(profileDataState.data) {
                if (name != null && address != null && phoneNumber != null) {
                    stateNameText = name
                    stateAddressText = address
                    stateWhatsAppNumberText = "0$phoneNumber"
                    hasProfile = true
                }
            }
            loadingState = false
        }
    }
    BackHandler {
        if (hasProfile) navHostController.popBackStack()
        else dialogState = true
    }
    Scaffold(
        topBar = {
            NavBarSecondary(
                title = stringResource(id = R.string.screen_edit_profile_information_nav_title),
                onBackButtonClick = {
                    if (hasProfile) navHostController.popBackStack()
                    else dialogState = true
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
                painter = painterResource(id = R.drawable.edit_profile_information_illustration),
                contentDescription = null,
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = stringResource(id = R.string.screen_edit_profile_information_title),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface,
                textAlign = TextAlign.Center
            )
            TextFieldPrimary(
                label = stringResource(id = R.string.name),
                value = stateNameText,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Text
                ),
                onValueChange = { value ->
                    if (value.length <= 32) {
                        stateNameText = value
                        stateNameError = false
                    } else {
                        stateNameError = true
                        stateNameErrorText =
                            context.getString(R.string.supporting_text_error_max_char, "32")
                    }
                },
                error = stateNameError,
                errorText = stateNameErrorText,
                modifier = Modifier
                    .fillMaxWidth()
            )
            TextFieldPrimary(
                label = stringResource(id = R.string.screen_edit_profile_whatsapp_number_label),
                value = stateWhatsAppNumberText,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.NumberPassword
                ),
                onValueChange = { value ->
                    if (value.length <= 13) {
                        stateWhatsAppNumberText = value
                        stateWhatsAppNumberError = false
                    } else {
                        stateWhatsAppNumberError = true
                        stateWhatsAppNumberErrorText =
                            context.getString(R.string.supporting_text_error_max_char, "13")
                    }
                },
                error = stateWhatsAppNumberError,
                errorText = stateWhatsAppNumberErrorText,
                modifier = Modifier
                    .fillMaxWidth()
            )
            TextFieldPrimary(
                label = stringResource(id = R.string.screen_edit_profile_full_address_label),
                value = stateAddressText,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done,
                    keyboardType = KeyboardType.Text
                ),
                onValueChange = { value ->
                    if (value.length <= 150) {
                        stateAddressText = value
                        stateAddressError = false
                    } else {
                        stateAddressError = true
                        stateAddressErrorText =
                            context.getString(R.string.supporting_text_error_max_char, "150")
                    }
                },
                error = stateAddressError,
                errorText = stateAddressErrorText,
                maxLines = Int.MAX_VALUE,
                modifier = Modifier
                    .fillMaxWidth()
            )
            Spacer(modifier = Modifier.weight(1f))
            ButtonPrimary(
                enabled = stateNameText.isNotEmpty() && stateWhatsAppNumberText.isNotEmpty()
                        && stateAddressText.isNotEmpty(),
                text = stringResource(id = R.string.save),
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    focusController.clearFocus(true)
                    keyboardController?.hide()
                    when {
                        (stateWhatsAppNumberText.isPhoneNumberInvalid()) -> {
                            stateWhatsAppNumberError = true
                            stateWhatsAppNumberErrorText =
                                context.getString(R.string.supporting_text_invalid_phone_number)
                        }

                        else -> {
                            editInformationScreenViewModel.saveProfile(
                                name = stateNameText,
                                phoneNumber = stateWhatsAppNumberText.toLong().toString(),
                                address = stateAddressText,
                            ) { success, message ->
                                if (success) {
                                    Toast(context, message).short()
                                    navHostController.navigateTo(NavRoute.DashboardScreen)
                                } else {
                                    Toast(context, message).long()
                                }
                            }
                        }
                    }
                }
            )
        }
    }
}

@Preview
@Composable
fun EditProfileInformationScreenPreview() {
    ADIKTheme {
        EditProfileInformationScreen(rememberNavController())
    }
}