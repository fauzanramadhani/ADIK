package com.fgr.adik.ui.screen.dashboard.account.edit_profile_information

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
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.fgr.adik.R
import com.fgr.adik.component.button.ButtonPrimary
import com.fgr.adik.component.navbar.NavBarSecondary
import com.fgr.adik.component.text_field.TextFieldPrimary
import com.fgr.adik.ui.theme.ADIKTheme
import com.fgr.adik.utils.isPhoneNumberInvalid

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun EditProfileInformationScreen(
    navHostController: NavHostController
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

    Scaffold(
        topBar = {
            NavBarSecondary(
                title = stringResource(id = R.string.screen_edit_profile_information_nav_title),
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
                    imeAction = ImeAction.Next,
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
fun EditProfileInformationScreenPreview() {
    ADIKTheme {
        EditProfileInformationScreen(rememberNavController())
    }
}