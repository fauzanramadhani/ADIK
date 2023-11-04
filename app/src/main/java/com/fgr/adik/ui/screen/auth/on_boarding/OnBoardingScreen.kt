package com.fgr.adik.ui.screen.auth.on_boarding

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.fgr.adik.R
import com.fgr.adik.component.button.ButtonLoginWithGoogle
import com.fgr.adik.component.button.ButtonPrimary
import com.fgr.adik.component.button.ButtonSecondary
import com.fgr.adik.component.navbar.NavBarPrimary
import com.fgr.adik.component.z9_others.DialogLoading
import com.fgr.adik.component.z9_others.HorizontalDiv
import com.fgr.adik.component.z9_others.Indicator
import com.fgr.adik.navigation.NavRoute
import com.fgr.adik.repository.utils.navigateToTop
import com.fgr.adik.ui.theme.ADIKTheme
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState

@OptIn(ExperimentalPagerApi::class, ExperimentalMaterial3Api::class)
@Composable
fun OnBoardingScreen(
    navHostController: NavHostController,
    onBoardingViewModel: OnBoardingViewModel = hiltViewModel()
) {
    if (onBoardingViewModel.currentUser != null) {
//        if (onBoardingViewModel.isEmailVerified) {
//            navHostController.navigateToTop(NavRoute.DashboardScreen)
//        } else {
//
//        }
        navHostController.navigateToTop(NavRoute.DashboardScreen)
    } else {
        val items = remember {
            OnBoardingItems.getData()
        }

        val pageState = rememberPagerState()
        var loadingState by rememberSaveable {
            mutableStateOf(false)
        }

        if (loadingState) {
            DialogLoading()
        }

        val googleSignInLauncher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.StartActivityForResult(), onResult = { result ->
                result.data?.let {
                    onBoardingViewModel.handleGoogleSignInResult(
                        data = it,
                        callback = { success ->
                            loadingState = false
                            if (success) {
                                navHostController.navigateToTop(NavRoute.DashboardScreen)
                            }
                        }
                    )
                }
            }
        )

        Scaffold(
            topBar = {
                NavBarPrimary()
            }
        ) { paddingValues ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
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
                HorizontalPager(
                    count = items.size,
                    state = pageState,
                    modifier = Modifier
                        .height(280.dp)
                ) { page ->
                    OnBoardingItem(items = items[page])
                }
                Spacer(modifier = Modifier.padding(vertical = 24.dp))
                // Indicators
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    repeat(items.size) {
                        Indicator(isSelected = it == pageState.currentPage)
                    }
                }
                Spacer(modifier = Modifier.padding(vertical = 24.dp))
                // Bottom Section
                BottomSection(
                    onRegisterClicked = {
                        navHostController.navigateToTop(NavRoute.RegisterScreen)
                    },
                    onLoginClicked = {
                        navHostController.navigateToTop(NavRoute.LoginScreen)
                    },
                    onLoginGoogleClicked = {
                        loadingState = true
                        googleSignInLauncher.launch(
                            onBoardingViewModel.loginWithGoogle()
                        )
                    }
                )
            }
        }
    }
}

@Composable
fun OnBoardingItem(items: OnBoardingItems) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = items.image),
            contentDescription = null,
            modifier = Modifier.padding(start = 50.dp, end = 50.dp)
        )

        Spacer(modifier = Modifier.height(36.dp))

        Text(
            text = stringResource(id = items.title),
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurface,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = stringResource(id = items.desc),
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onBackground,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun BottomSection(
    onRegisterClicked: () -> Unit = {},
    onLoginClicked: () -> Unit = {},
    onLoginGoogleClicked: () -> Unit = {},
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(24.dp),
    ) {
        // Button Login with Email
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            ButtonSecondary(
                text = stringResource(id = R.string.register),
                modifier = Modifier
                    .weight(1f),
                onClick = onRegisterClicked
            )
            Spacer(modifier = Modifier.weight(0.2f))
            ButtonPrimary(
                text = stringResource(id = R.string.login),
                modifier = Modifier
                    .weight(1f),
                onClick = onLoginClicked
            )
        }
        // Spacer
        Row(
            horizontalArrangement = Arrangement.spacedBy(24.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            HorizontalDiv(
                modifier = Modifier
                    .weight(0.4f)
            )
            Text(
                text = stringResource(id = R.string.or),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.secondary,
                textAlign = TextAlign.Center
            )
            HorizontalDiv(
                modifier = Modifier
                    .weight(0.4f)
            )
        }
        // Login with Google
        ButtonLoginWithGoogle(
            modifier = Modifier
                .fillMaxWidth(),
            onClick = onLoginGoogleClicked
        )
    }
}

class OnBoardingItems(
    val image: Int,
    val title: Int,
    val desc: Int
) {
    companion object {
        fun getData(): List<OnBoardingItems> {
            return listOf(
                OnBoardingItems(
                    R.drawable.attendance_illustration,
                    R.string.screen_on_boarding_1_title,
                    R.string.screen_on_boarding_1_description
                ),
                OnBoardingItems(
                    R.drawable.location_illustration,
                    R.string.screen_on_boarding_2_title,
                    R.string.screen_on_boarding_2_description
                ),
            )
        }
    }
}

@Preview
@Composable
fun OnBoardingScreenPreview() {
    ADIKTheme {
        OnBoardingScreen(rememberNavController())
    }
}