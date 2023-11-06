package com.fgr.adik.ui.screen.auth.email_verification

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.fgr.adik.R
import com.fgr.adik.component.button.ButtonSecondary
import com.fgr.adik.component.dialog.DialogAlert
import com.fgr.adik.component.navbar.NavBarSecondary
import com.fgr.adik.component.z9_others.DialogLoading
import com.fgr.adik.navigation.NavRoute
import com.fgr.adik.ui.theme.ADIKTheme
import com.fgr.adik.utils.Toast
import com.fgr.adik.utils.UiState
import com.fgr.adik.utils.navigateToTop

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmailVerificationScreen(
    navHostController: NavHostController,
    emailVerificationViewModel: EmailVerificationViewModel = hiltViewModel(),
) {
    val context = LocalContext.current
    if (emailVerificationViewModel.firebaseCurrentUser()?.isEmailVerified == true) {
        navHostController.navigateToTop(NavRoute.DashboardScreen)
    } else {
        var dialogState by rememberSaveable {
            mutableStateOf(false)
        }
        if (dialogState) {
            DialogAlert(
                title = stringResource(id = R.string.screen_email_title_dialog_alert_logout),
                message = stringResource(id = R.string.screen_email_message_dialog_alert_logout),
                confirmText = stringResource(id = R.string.out),
                dismissText = stringResource(id = R.string.cancel),
                onConfirm = {
                    emailVerificationViewModel.logout()
                    navHostController.navigateToTop(NavRoute.OnBoardingScreen)
                    dialogState = false
                },
                onDismiss = {
                    dialogState = false
                }
            )
        }
        BackHandler {
            dialogState = true
        }
        var loadingDialogState by remember {
            mutableStateOf(false)
        }

        if (loadingDialogState) {
            DialogLoading()
        }

        val currentCountDownState =
            emailVerificationViewModel.currentCountDown.collectAsState().value

        val sendEmailState = emailVerificationViewModel.sendEmailState.collectAsState().value
        LaunchedEffect(sendEmailState) {
            when (sendEmailState) {
                is UiState.Loading -> {
                    loadingDialogState = true
                }
                is UiState.Success -> {
                    loadingDialogState = false
                    Toast(context, sendEmailState.data).long()
                }

                is UiState.Error -> {
                    loadingDialogState = false
                    Toast(context, sendEmailState.errorMessage).long()
                }
                else -> {}
            }
        }

        Scaffold(
            topBar = {
                NavBarSecondary(
                    title = stringResource(id = R.string.screen_email_verification_nav_title),
                    onBackButtonClick = {
                        dialogState = true
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
                Text(
                    text = stringResource(id = R.string.screen_email_verification_title),
                    style = typography.titleLarge,
                    color = colorScheme.onSurface,
                    textAlign = TextAlign.Center
                )
                Image(
                    painter = painterResource(id = R.drawable.mailbox_illustration),
                    contentDescription = null,
                )
                Text(
                    text = stringResource(id = R.string.screen_email_verification_description),
                    style = typography.bodyMedium,
                    color = colorScheme.onSurface,
                    textAlign = TextAlign.Center
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(top = 24.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.screen_email_verification_is_already_verified),
                        style = typography.labelLarge,
                        color = colorScheme.onSurface,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    ButtonSecondary(
                        text = stringResource(id = R.string.refresh),
                        modifier = Modifier.width(114.dp)
                    ) {
                        if (emailVerificationViewModel.firebaseCurrentUser()?.isEmailVerified == true) {
                            navHostController.navigateToTop(NavRoute.OnBoardingScreen)
                        }
                    }
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(id = R.string.screen_email_verification_still_not_get),
                        style = typography.labelLarge,
                        color = colorScheme.onSurface,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    ButtonSecondary(
                        enabled = currentCountDownState == 0L,
                        text = if (currentCountDownState == 0L) {
                            stringResource(id = R.string.screen_email_resend_button_text)
                        } else {
                            currentCountDownState.toString()
                        },
                        modifier = Modifier.width(114.dp)
                    ) {
                        emailVerificationViewModel.sendEmailVerification()
                    }
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(id = R.string.screen_email_verification_back_to_login),
                        style = typography.labelLarge,
                        color = colorScheme.onSurface,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    ButtonSecondary(
                        text = stringResource(id = R.string.out),
                        modifier = Modifier.width(114.dp)
                    ) {
                        dialogState = true
                    }
                }
            }
        }
    }
}

//
@Preview
@Composable
fun EmailVerificationScreenPreview() {
    ADIKTheme {
        EmailVerificationScreen(rememberNavController())
    }
}