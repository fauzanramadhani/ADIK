package com.fgr.adik.ui.screen.dashboard.account

import android.annotation.SuppressLint
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.fgr.adik.R
import com.fgr.adik.component.button.ButtonTextIconLarge
import com.fgr.adik.component.dialog.DialogAlert
import com.fgr.adik.component.navbar.NavBarPrimary
import com.fgr.adik.component.z9_others.DialogLoading
import com.fgr.adik.component.z9_others.ErrorMessageAndAction
import com.fgr.adik.component.z9_others.HorizontalDiv
import com.fgr.adik.component.z9_others.LoadImageUrl
import com.fgr.adik.component_core.icon.BaseCircleIconBox
import com.fgr.adik.data.response.ProfileResponse
import com.fgr.adik.navigation.NavRoute
import com.fgr.adik.ui.theme.ADIKTheme
import com.fgr.adik.utils.Toast
import com.fgr.adik.utils.UiState
import com.fgr.adik.utils.navigateTo
import com.fgr.adik.utils.navigateToTop

@Composable
fun AccountScreen(
    navHostController: NavHostController,
    myPaddingValues: PaddingValues,
    contentRoute: MutableState<Int>,
    accountScreenViewModel: AccountScreenViewModel = hiltViewModel(),
) {
    val context = LocalContext.current
    val pickImageLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            if (uri != null) {
                // Image selected successfully from gallery, proceed to sending to API
                accountScreenViewModel.uploadImageProfile(
                    uri = uri,
                )
            }
        }
    var profileData by remember {
        mutableStateOf(ProfileResponse())
    }
    var errorState by rememberSaveable {
        mutableStateOf(false)
    }
    var errorText by rememberSaveable {
        mutableStateOf("")
    }
    var dialogAlertLogoutState by rememberSaveable {
        mutableStateOf(false)
    }
    val loadingState = remember {
        mutableStateListOf(false, false)
    }
    if (loadingState[0] || loadingState[1]) {
        DialogLoading()
    }
    if (dialogAlertLogoutState) {
        DialogAlert(
            title = stringResource(id = R.string.screen_email_title_dialog_alert_logout),
            message = stringResource(id = R.string.screen_email_message_dialog_alert_logout),
            confirmText = stringResource(id = R.string.out),
            dismissText = stringResource(id = R.string.cancel),
            onConfirm = {
                loadingState[0] = true
                accountScreenViewModel.logout {
                    loadingState[0] = false
                    dialogAlertLogoutState = false
                    navHostController.navigateToTop(NavRoute.OnBoardingScreen)
                }
            },
            onDismiss = {
                dialogAlertLogoutState = false
            }
        )
    }
    when (val getProfileState = accountScreenViewModel.getProfileState.collectAsState().value) {
        UiState.Empty -> {
            loadingState[0] = false
            errorState = false
        }

        is UiState.Error -> {
            loadingState[0] = false
            errorState = true
            errorText = getProfileState.errorMessage
        }

        UiState.Loading -> {
            loadingState[0] = true
            errorState = false
        }

        is UiState.Success -> {
            loadingState[0] = false
            errorState = false
            profileData = getProfileState.data
        }
    }

    val uploadProfileState = accountScreenViewModel.uploadProfileImageState.collectAsState()
    LaunchedEffect(uploadProfileState.value) {
        when (val currentState = uploadProfileState.value) {
            UiState.Empty -> {
                loadingState[1] = false
                errorState = false
            }

            is UiState.Error -> {
                loadingState[1] = false
                Toast(context, currentState.errorMessage).long()
            }

            UiState.Loading -> {
                loadingState[1] = true
                errorState = false
            }

            is UiState.Success -> {
                loadingState[1] = false
                errorState = false
                accountScreenViewModel.getProfile()
                Toast(context, "Berhasil mengubah gambar profile").long()
            }
        }
    }

    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier
            .background(colorScheme.background)
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(bottom = myPaddingValues.calculateBottomPadding() + 24.dp)
    ) {
        NavBarPrimary() {
            // TODO: On support button clicked
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(colorScheme.surface)
        ) {
            Text(
                text = stringResource(id = R.string.profile),
                style = typography.labelLarge,
                color = colorScheme.onSurface,
                modifier = Modifier
                    .padding(24.dp)
            )
            HorizontalDiv()
            if (errorState) {
                ErrorMessageAndAction(
                    modifier = Modifier
                        .padding(24.dp),
                    message = errorText,
                    actionButtonText = stringResource(id = R.string.refresh),
                ) {
                    accountScreenViewModel.getProfile()
                }
            } else {
                Column(
                    verticalArrangement = Arrangement.spacedBy(4.dp),
                    modifier = Modifier
                        .padding(24.dp)
                ) {
                    Box {
                        LoadImageUrl(
                            url = profileData.imageProfileUrl ?: "https://adik-api.neodigitalcreation.my.id/public/images/default/default.jpeg",
                            modifier = Modifier
                                .size(90.dp)
                                .clip(CircleShape)
                        )
                        Box(
                            modifier = Modifier
                                .background(colorScheme.surface, shape = CircleShape)
                                .padding(4.dp)
                                .align(Alignment.BottomEnd),
                        ) {
                            BaseCircleIconBox(
                                enabled = true,
                                onClick = {
                                    pickImageLauncher.launch("image/*")
                                }
                            ) {
                                Icon(
                                    imageVector = Icons.Outlined.Edit,
                                    contentDescription = null,
                                    tint = colorScheme.onPrimary,
                                    modifier = Modifier
                                        .align(Alignment.Center)
                                )
                            }
                        }
                    }
                    Text(
                        text = profileData.name ?: "",
                        style = typography.bodyMedium,
                        color = colorScheme.onSurface,
                        modifier = Modifier
                            .padding(top = 8.dp)
                    )
                    Text(
                        text = profileData.email ?: "",
                        style = typography.bodySmall,
                        color = colorScheme.secondary,
                    )
                    if (!profileData.phoneNumber.isNullOrEmpty()) {
                        Text(
                            text = "0${profileData.phoneNumber}",
                            style = typography.bodySmall,
                            color = colorScheme.secondary,
                        )
                    }
                }
                HorizontalDiv()
                // Furlough request button
                ButtonTextIconLarge(
                    icon = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_furlough),
                            contentDescription = null,
                            tint = colorScheme.onPrimary,
                            modifier = Modifier
                                .fillMaxSize()
                                .align(Alignment.Center)
                        )
                    },
                    title = stringResource(id = R.string.screen_account_screen_furlough_request_button)
                ) {
                    // TODO
                }
                HorizontalDiv()
                // Edit profile information button
                ButtonTextIconLarge(
                    icon = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_edit_profile_information),
                            contentDescription = null,
                            tint = colorScheme.onPrimary,
                            modifier = Modifier
                                .fillMaxSize()
                                .align(Alignment.Center)
                        )
                    },
                    title = stringResource(id = R.string.screen_account_screen_edit_profile_information_button)
                ) {
                    navHostController.navigateTo(NavRoute.EditProfileInformationScreen)
                }
                HorizontalDiv()
                // Change password button
                if (!accountScreenViewModel.isLoginMethodGoogle) {
                    ButtonTextIconLarge(
                        icon = {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_lock),
                                contentDescription = null,
                                tint = colorScheme.onPrimary,
                                modifier = Modifier
                                    .fillMaxSize()
                                    .align(Alignment.Center)
                            )
                        },
                        title = stringResource(id = R.string.screen_account_screen_change_password_button)
                    ) {
                        navHostController.navigateTo(NavRoute.ChangePasswordScreen)
                    }
                    HorizontalDiv()
                }
                // Logout button
                ButtonTextIconLarge(
                    icon = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_logout),
                            contentDescription = null,
                            tint = colorScheme.onPrimary,
                            modifier = Modifier
                                .fillMaxSize()
                                .align(Alignment.Center)
                        )
                    },
                    title = stringResource(id = R.string.exit),
                    iconBackgroundColor = colorScheme.error,
                    textColor = colorScheme.error,
                    arrowColor = colorScheme.error
                ) {
                    dialogAlertLogoutState = true
                }
            }
        }
    }
}

@SuppressLint("UnrememberedMutableState")
@Preview
@Composable
fun AccountScreenPreview() {
    ADIKTheme {
        AccountScreen(
            navHostController = rememberNavController(),
            myPaddingValues = PaddingValues(bottom = 48.dp),
            contentRoute = mutableIntStateOf(0)
        )
    }
}