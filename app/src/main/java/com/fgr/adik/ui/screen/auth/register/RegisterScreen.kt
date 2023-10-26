package com.fgr.adik.ui.screen.auth.register

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.fgr.adik.ui.theme.ADIKTheme

@Composable
fun RegisterScreen(
    navHostController: NavHostController,
) {
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

//    TextFieldPassword(
//        label = stringResource(id = R.string.screen_login_password_confirmation_label),
//        value = statePasswordConfirmationText,
//        onValueChange = { value ->
//            if (value.length < 32) {
//                statePasswordConfirmationText = value
//                statePasswordConfirmationError = false
//            } else {
//                statePasswordConfirmationError = true
//                statePasswordConfirmationErrorText =
//                    context.getString(R.string.supporting_text_error_max_char, "32")
//            }
//        },
//        error = statePasswordConfirmationError,
//        errorText = statePasswordConfirmationErrorText,
//        visibility = statePasswordConfirmationVisibility,
//        onVisibilityChange = {
//            statePasswordConfirmationVisibility = !statePasswordConfirmationVisibility
//        },
//        modifier = Modifier
//            .fillMaxWidth()
//    )
}

@Preview
@Composable
fun RegisterScreenPrev() {
    ADIKTheme {
        RegisterScreen(rememberNavController())
    }
}