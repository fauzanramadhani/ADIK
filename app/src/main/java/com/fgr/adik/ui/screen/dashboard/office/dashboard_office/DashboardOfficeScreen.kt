package com.fgr.adik.ui.screen.dashboard.office.dashboard_office

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.fgr.adik.R
import com.fgr.adik.component.button.ButtonTextIconLarge
import com.fgr.adik.component.navbar.NavBarSecondary
import com.fgr.adik.component.z9_others.DialogLoading
import com.fgr.adik.component.z9_others.ErrorMessageAndAction
import com.fgr.adik.component.z9_others.HorizontalDiv
import com.fgr.adik.component.z9_others.LoadImageUrl
import com.fgr.adik.navigation.NavRoute
import com.fgr.adik.ui.theme.ADIKTheme
import com.fgr.adik.utils.UiState
import com.fgr.adik.utils.navigateToTop

@Composable
fun DashboardOfficeScreen(
    navHostController: NavHostController,
    officeId: String? = null,
    dashboardOfficeViewModel: DashboardOfficeViewModel = hiltViewModel(),
) {
    val context = LocalContext.current
    var loadingState by rememberSaveable {
        mutableStateOf(false)
    }

    if (loadingState) {
        DialogLoading()
    }
    LaunchedEffect(dashboardOfficeViewModel) {
        dashboardOfficeViewModel.getOfficeById(officeId ?: "")
    }
    val getOfficeByIdState =
        dashboardOfficeViewModel.getOfficeByIdState.collectAsStateWithLifecycle()

    when (val currentState = getOfficeByIdState.value) {
        UiState.Empty -> {
            // Nothing
        }

        is UiState.Error -> {
            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                ErrorMessageAndAction(
                    modifier = Modifier
                        .align(Alignment.Center),
                    message = currentState.errorMessage,
                    actionButtonText = stringResource(
                        id = if (currentState.errorMessage.equals(
                                "User not found",
                                true
                            )
                        ) R.string.exit
                        else R.string.refresh
                    )
                ) {
                    if (currentState.errorMessage.equals("User not found", true)) {
                        loadingState = true
                        dashboardOfficeViewModel.logout {
                            loadingState = false
                            navHostController.navigateToTop(NavRoute.OnBoardingScreen)
                        }

                    } else {
                        dashboardOfficeViewModel.getOfficeById(officeId ?: "")
                    }
                }
            }
        }

        UiState.Loading -> {
            DialogLoading()
        }

        is UiState.Success -> {
            BackHandler {
                navHostController.navigate(NavRoute.DashboardScreen.route)
            }
            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier
                    .fillMaxSize()
                    .background(colorScheme.background)
                    .verticalScroll(rememberScrollState())
            ) {
                NavBarSecondary(
                    title = "Dashboard Kantor",
                    onBackButtonClick = {
                        navHostController.navigate(NavRoute.DashboardScreen.route)
                    },
                    onSupportButtonClick = {
                        // TODO: On support button clicked
                    }
                )
                Column(
                    modifier = Modifier
                        .background(colorScheme.surface)
                ) {
                    Text(
                        text = "Kantor",
                        style = typography.labelLarge,
                        color = colorScheme.onSurface,
                        modifier = Modifier
                            .padding(24.dp)
                    )
                    HorizontalDiv()
                    Column(
                        modifier = Modifier
                            .padding(24.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(84.dp)
                                .clip(RoundedCornerShape(4.dp))
                                .border(
                                    shape = RoundedCornerShape(4.dp),
                                    width = 1.dp,
                                    color = colorScheme.outline
                                )
                        ) {
                            if (currentState.data.officeImageUrl != null && currentState.data.officeImageUrl != "") {
                                LoadImageUrl(
                                    url = currentState.data.officeImageUrl,
                                    modifier = Modifier
                                        .fillMaxSize()
                                )
                            } else {
                                Image(
                                    painter = painterResource(id = R.drawable.office_default_image),
                                    contentDescription = null,
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier
                                        .fillMaxSize()
                                )
                            }

                        }
                        Spacer(modifier = Modifier.padding(top = 12.dp))
                        Text(
                            text = currentState.data.name ?: "",
                            style = typography.titleSmall,
                            color = colorScheme.onSurface,
                        )
                        Spacer(modifier = Modifier.padding(top = 4.dp))
                        Text(
                            text = currentState.data.address ?: "",
                            style = typography.bodySmall,
                            color = colorScheme.onSurface,
                        )
                    }
                    HorizontalDiv()
                    // Menu Member
                    ButtonTextIconLarge(
                        icon = {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_user),
                                contentDescription = null,
                                tint = colorScheme.onPrimary,
                                modifier = Modifier
                                    .fillMaxSize()
                                    .align(Alignment.Center)
                            )
                        },
                        title = "Anggota"
                    ) {
                        // TODO: On member menu clicked
                    }
                    HorizontalDiv()
                    // Menu Monthly
                    ButtonTextIconLarge(
                        icon = {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_rank),
                                contentDescription = null,
                                tint = colorScheme.onPrimary,
                                modifier = Modifier
                                    .fillMaxSize()
                                    .align(Alignment.Center)
                            )
                        },
                        title = "Peringkat Bulanan"
                    ) {
                        // TODO: on monthly rank menu clicked
                    }
                    HorizontalDiv()
                    // Menu Contact WhatsApp Manager
                    ButtonTextIconLarge(
                        icon = {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_whatsapp),
                                contentDescription = null,
                                tint = colorScheme.onPrimary,
                                modifier = Modifier
                                    .fillMaxSize()
                                    .align(Alignment.Center)
                            )
                        },
                        title = "Hubungi WhatsApp Pengelola"
                    ) {
                        // TODO: On contact whatsapp manager menu clicked
                    }
                    HorizontalDiv()
                    // Menu Exit Office
                    ButtonTextIconLarge(
                        iconBackgroundColor = colorScheme.error,
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
                        title = "Keluar Kantor",
                        textColor = colorScheme.error,
                        arrowColor = colorScheme.error
                    ) {
                        navHostController.navigate(NavRoute.ExitOfficeScreen.navigateWithId(
                            officeId = officeId ?: ""
                        ))
                    }
                    HorizontalDiv()
                }
                // Employee Menu
                Column(
                    modifier = Modifier
                        .background(colorScheme.surface)
                ) {
                    Text(
                        text = "Menu Karyawan",
                        style = typography.labelLarge,
                        color = colorScheme.onSurface,
                        modifier = Modifier
                            .padding(24.dp)
                    )
                    HorizontalDiv()
                    // Menu My Absence
                    ButtonTextIconLarge(
                        icon = {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_my_absence),
                                contentDescription = null,
                                tint = colorScheme.onPrimary,
                                modifier = Modifier
                                    .fillMaxSize()
                                    .align(Alignment.Center)
                            )
                        },
                        title = "Absensi Saya",
                    ) {
                        // TODO: On my absence menu clicked
                    }
                    HorizontalDiv()
                    // Menu My Furlough
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
                        title = "Cuti Saya",
                    ) {
                        // TODO: On my absence menu clicked
                    }
                    HorizontalDiv()
                }
                if (currentState.data.role.equals(
                        "owner",
                        true
                    ) || currentState.data.role.equals("admin", true)
                ) {
                    // Manager Menu
                    Column(
                        modifier = Modifier
                            .background(colorScheme.surface)
                    ) {
                        Text(
                            text = "Menu Pengelola",
                            style = typography.labelLarge,
                            color = colorScheme.onSurface,
                            modifier = Modifier
                                .padding(24.dp)
                        )
                        HorizontalDiv()
                        // Menu Invitation Center
                        ButtonTextIconLarge(
                            icon = {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_add_users),
                                    contentDescription = null,
                                    tint = colorScheme.onPrimary,
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .align(Alignment.Center)
                                )
                            },
                            title = "Pusat Undangan",
                        ) {
                            // TODO: On my absence menu clicked
                        }
                        HorizontalDiv()
                        // Menu Invitation Center
                        ButtonTextIconLarge(
                            icon = {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_shift),
                                    contentDescription = null,
                                    tint = colorScheme.onPrimary,
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .align(Alignment.Center)
                                )
                            },
                            title = "Shift Kerja",
                        ) {
                            // TODO: On my absence menu clicked
                        }
                        HorizontalDiv()
                        // Menu Working Schedule
                        ButtonTextIconLarge(
                            icon = {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_calendar),
                                    contentDescription = null,
                                    tint = colorScheme.onPrimary,
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .align(Alignment.Center)
                                )
                            },
                            title = "Jadwal Kerja",
                        ) {
                            // TODO: On my absence menu clicked
                        }
                        HorizontalDiv()
                        // Menu Approval
                        ButtonTextIconLarge(
                            icon = {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_approval),
                                    contentDescription = null,
                                    tint = colorScheme.onPrimary,
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .align(Alignment.Center)
                                )
                            },
                            title = "Persetujuan",
                        ) {
                            // TODO: On my absence menu clicked
                        }
                        HorizontalDiv()
                        // Menu Subscription
                        ButtonTextIconLarge(
                            icon = {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_subscription),
                                    contentDescription = null,
                                    tint = colorScheme.onPrimary,
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .align(Alignment.Center)
                                )
                            },
                            title = "Langganan",
                        ) {
                            // TODO: On my absence menu clicked
                        }
                        HorizontalDiv()
                        // Menu Subscription
                        ButtonTextIconLarge(
                            icon = {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_setting),
                                    contentDescription = null,
                                    tint = colorScheme.onPrimary,
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .align(Alignment.Center)
                                )
                            },
                            title = "Setting",
                        ) {
                            // TODO: On my absence menu clicked
                        }
                        HorizontalDiv()
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun DashboardOfficeScreenPreview() {
    ADIKTheme {
        DashboardOfficeScreen(rememberNavController())
    }
}