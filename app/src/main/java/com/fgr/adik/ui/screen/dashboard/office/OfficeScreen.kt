package com.fgr.adik.ui.screen.dashboard.office

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import com.fgr.adik.component.button.ButtonPrimary
import com.fgr.adik.component.button.ButtonSecondary
import com.fgr.adik.component.item.ItemOfficeScreen
import com.fgr.adik.component.loading.SkeletonLoading
import com.fgr.adik.component.navbar.NavBarPrimary
import com.fgr.adik.component.z9_others.DialogLoading
import com.fgr.adik.component.z9_others.ErrorMessageAndAction
import com.fgr.adik.component.z9_others.HorizontalDiv
import com.fgr.adik.navigation.NavRoute
import com.fgr.adik.ui.theme.ADIKTheme
import com.fgr.adik.utils.UiState
import com.fgr.adik.utils.navigateTo
import com.fgr.adik.utils.navigateToTop

@Composable
fun OfficeScreen(
    navHostController: NavHostController,
    myPaddingValues: PaddingValues,
    contentRoute: MutableState<Int>,
    officeViewModel: OfficeViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    var loadingState by rememberSaveable {
        mutableStateOf(false)
    }
    if (loadingState) {
        DialogLoading()
    }
    LaunchedEffect(loadingState) {
        if (loadingState) {
            Log.e("OfficeSc", "Loading yes")
        }
    }
    val officeList = officeViewModel.getOfficeIdState.collectAsStateWithLifecycle()

    LazyColumn(
        modifier = Modifier
            .background(colorScheme.background)
            .padding(myPaddingValues)
    ) {
        item {
            NavBarPrimary() {
                // TODO: On support button clicked
            }
        }
        item {
            Spacer(modifier = Modifier.padding(vertical = 6.dp))
        }
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(colorScheme.surface)
                    .padding(24.dp)
            ) {
                Text(
                    text = "Tambah Kantor",
                    style = MaterialTheme.typography.titleSmall,
                    color = colorScheme.onSurface
                )
                Spacer(modifier = Modifier.padding(top = 4.dp))
                Text(
                    text = "Anda dapat bergabung dengan kantor yang sudah ada atau membuat kantor Anda sendiri.",
                    style = typography.bodySmall,
                    color = colorScheme.onSurface
                )
                Spacer(modifier = Modifier.padding(top = 24.dp))
                Row(
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    ButtonSecondary(
                        modifier = Modifier
                            .weight(0.8f),
                        text = "Buat"
                    ) {
                        navHostController.navigateTo(NavRoute.CreateOfficeScreen)
                    }
                    ButtonPrimary(
                        modifier = Modifier
                            .weight(0.8f),
                        text = "Gabung"
                    )
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        }
        item {
            Spacer(modifier = Modifier.padding(vertical = 6.dp))
        }
        item {
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .background(colorScheme.surface)
                    .padding(24.dp)
            ) {
                Text(
                    text = "Kantor",
                    style = MaterialTheme.typography.titleSmall
                )
                Text(
                    text = "Daftar kantor Anda.",
                    style = typography.bodySmall
                )
            }
        }
        when (val currentState = officeList.value) {
            UiState.Empty -> {
                // Nothing
            }

            is UiState.Error -> {
                item {
                    Column(
                        modifier = Modifier
                            .background(colorScheme.surface)
                    ) {
                        HorizontalDiv()
                        Box(
                            modifier = Modifier
                                .padding(24.dp)
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
                                    officeViewModel.logout {
                                        loadingState = false
                                        navHostController.navigateToTop(NavRoute.OnBoardingScreen)
                                    }

                                } else {
                                    officeViewModel.getMyOffice()
                                }
                            }
                        }
                    }
                }
            }

            UiState.Loading -> {
                items(3) {
                    Row(
                        modifier = Modifier
                            .background(colorScheme.surface)
                            .padding(bottom = 24.dp),
                    ) {
                        SkeletonLoading(
                            modifier = Modifier
                                .padding(horizontal = 24.dp)
                                .height(88.dp)
                                .fillMaxWidth()
                        )
                        Spacer(modifier = Modifier.padding(bottom = 12.dp))
                    }
                }
            }

            is UiState.Success -> {
                if (currentState.data.isNotEmpty()) {
                    items(currentState.data) {
                        Column {
                            HorizontalDiv()
                            ItemOfficeScreen(
                                officeImageUrl = it.officeImageUrl,
                                officeName = it.officeName,
                                access =
                                if (it.officeAccess.equals("owner", true))
                                    "Pengelola Utama"
                                else if (it.officeAccess.equals("admin", true))
                                    "Pengelola"
                                else "Anggota",
                                division =
                                it.officeDivision.ifEmpty { "-" },
                            ) {
                                navHostController.navigate(
                                    NavRoute.DashboardOfficeScreen.navigateWithId(
                                        it.officeId
                                    )
                                )
                            }
                        }
                    }
                } else {
                    item {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                                .padding(24.dp)
                        ) {
                            Text(
                                text = "Hmmm...",
                                style = typography.labelMedium,
                                color = colorScheme.onSurface,
                                modifier = Modifier
                                    .padding(bottom = 6.dp)
                            )
                            Text(
                                text = "Sepertinya Anda belum bergabung kedalam kantor manapun.",
                                style = typography.bodySmall,
                                color = colorScheme.onSurface,
                                modifier = Modifier
                                    .padding(bottom = 24.dp)
                            )
                            Image(
                                painter = painterResource(id = R.drawable.empty_illustration),
                                contentDescription = null,
                            )
                        }
                    }
                }

            }
        }
    }
}

@SuppressLint("UnrememberedMutableState")
@Preview
@Composable
fun OfficeScreenPreview() {
    ADIKTheme {
        OfficeScreen(
            navHostController = rememberNavController(),
            myPaddingValues = PaddingValues(bottom = 48.dp),
            contentRoute = mutableIntStateOf(1)
        )
    }
}