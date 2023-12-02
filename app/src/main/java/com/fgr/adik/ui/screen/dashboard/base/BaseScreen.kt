package com.fgr.adik.ui.screen.dashboard.base

import android.app.Activity
import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Fill
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
import com.fgr.adik.component.z9_others.DialogLoading
import com.fgr.adik.component.z9_others.ErrorMessageAndAction
import com.fgr.adik.navigation.NavRoute
import com.fgr.adik.ui.screen.dashboard.account.AccountScreen
import com.fgr.adik.ui.screen.dashboard.home.HomeScreen
import com.fgr.adik.ui.screen.dashboard.office.OfficeScreen
import com.fgr.adik.ui.theme.ADIKTheme
import com.fgr.adik.utils.UiState
import com.fgr.adik.utils.navigateToTop

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BaseScreen(
    navHostController: NavHostController,
    baseScreenViewModel: BaseScreenViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val contentRoute = rememberSaveable { mutableIntStateOf(0) }
    var loadingState by rememberSaveable {
        mutableStateOf(false)
    }

    if (loadingState) {
        DialogLoading()
    }

    BackHandler {
        if (contentRoute.intValue == 0) {
            (context as Activity).finish()
        } else {
            contentRoute.intValue = 0
        }
    }
    when (val hasProfile = baseScreenViewModel.hasProfile.collectAsStateWithLifecycle().value) {
        UiState.Empty -> {}
        is UiState.Error -> {
            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                ErrorMessageAndAction(
                    modifier = Modifier
                        .align(Alignment.Center),
                    message = hasProfile.errorMessage,
                    actionButtonText = stringResource(
                        id = if (hasProfile.errorMessage.equals(
                                "User not found",
                                true
                            )
                        ) R.string.exit
                        else R.string.refresh
                    )
                ) {
                    if (hasProfile.errorMessage.equals("User not found", true)) {
                        loadingState = true
                        baseScreenViewModel.logout {
                            loadingState = false
                            navHostController.navigateToTop(NavRoute.OnBoardingScreen)
                        }

                    } else {
                        baseScreenViewModel.isProfileInformationIsNotEmpty()
                    }
                }
            }
        }

        UiState.Loading -> {
            DialogLoading()
        }

        is UiState.Success -> {
            if (hasProfile.data) {
                var myPaddingValues by remember {
                    mutableStateOf(PaddingValues())
                }

                val screens = listOf(
                    DashboardContent(
                        title = stringResource(id = R.string.home),
                        icon = R.drawable.ic_home,
                        content = {
                            HomeScreen(
                                navHostController,
                                myPaddingValues,
                                contentRoute
                            )
                        }
                    ),
                    DashboardContent(
                        title = stringResource(id = R.string.office),
                        icon = R.drawable.ic_office,
                        content = {
                            OfficeScreen(
                                navHostController,
                                myPaddingValues,
                                contentRoute
                            )
                        }
                    ),
                    DashboardContent(
                        title = stringResource(id = R.string.account),
                        icon = R.drawable.ic_account,
                        content = {
                            AccountScreen(
                                navHostController,
                                myPaddingValues,
                                contentRoute
                            )
                        }
                    ),
                )
                val currentTheme = isSystemInDarkTheme()

                val shadowColor = if (currentTheme) {
                    Color.Black.copy(alpha = 0.2f) // Dark Theme
                } else {
                    Color.Black.copy(alpha = 0.02f) // Light Theme
                }
                val shadowElevation = 1.dp

                Scaffold(
                    bottomBar = {
                        NavigationBar(
                            containerColor = if (currentTheme) {
                                colorScheme.surface
                            } else {
                                colorScheme.onPrimary
                            },
                            modifier = Modifier.drawWithContent {
                                drawContent()
                                drawRect(
                                    color = shadowColor,
                                    topLeft = Offset(0f, size.height),
                                    size = Size(size.width, shadowElevation.toPx()),
                                    style = Fill
                                )
                                drawRect(
                                    color = shadowColor,
                                    topLeft = Offset(
                                        -shadowElevation.toPx(),
                                        -shadowElevation.toPx()
                                    ),
                                    size = Size(
                                        size.width + shadowElevation.toPx() * 2,
                                        shadowElevation.toPx() * 2
                                    ),
                                    style = Fill
                                )
                            }
                        ) {
                            screens.forEachIndexed { index, item ->
                                val selected = contentRoute.intValue == index


                                NavigationBarItem(
                                    selected = selected,
                                    colors = NavigationBarItemDefaults.colors(
                                        indicatorColor = colorScheme.primaryContainer
                                    ),
                                    onClick = {
                                        contentRoute.intValue = index
                                    },
                                    label = {
                                        Text(
                                            text = item.title,
                                            style = typography.labelMedium,
                                            color = if (selected) colorScheme.onSurface else colorScheme.secondary
                                        )
                                    },
                                    icon = {
                                        Icon(
                                            painter = painterResource(item.icon),
                                            tint = if (selected) colorScheme.primary else colorScheme.secondary,
                                            contentDescription = null,
                                        )
                                    }
                                )
                            }
                        }
                    }
                ) { paddingValues ->
                    myPaddingValues = paddingValues
                    screens[contentRoute.intValue].content.invoke(myPaddingValues)
                }

                val navBackStackEntry = navHostController.previousBackStackEntry?.destination?.route
                // When Back Stack Entry happens
                LaunchedEffect(navBackStackEntry) {
                    if (navBackStackEntry != null && navBackStackEntry != NavRoute.DashboardScreen.route) {
                        // Set the index into the position
                        Log.e("baseScreen", navBackStackEntry)
                        contentRoute.intValue =
                            getIndexFromDestination(navBackStackEntry)
                    }
                }
            } else {
                navHostController.navigateToTop(NavRoute.EditProfileInformationScreen)
            }
        }
    }

}

private fun getIndexFromDestination(route: String?): Int {
    return when (route) {
        // Set the index position based on Back Stack Entry
        NavRoute.DashboardOfficeScreen.route -> 1
        NavRoute.EditProfileInformationScreen.route -> 2
        else -> 0
    }
}

data class DashboardContent(
    val title: String,
    val icon: Int,
    val content: @Composable (PaddingValues) -> Unit = {}
)

@Preview
@Composable
fun BaseScreenPreview() {
    ADIKTheme {
        BaseScreen(navHostController = rememberNavController())
    }
}