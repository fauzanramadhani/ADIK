package com.fgr.adik.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.fgr.adik.ui.screen.auth.email_verification.EmailVerificationScreen
import com.fgr.adik.ui.screen.auth.login.LoginScreen
import com.fgr.adik.ui.screen.auth.on_boarding.OnBoardingScreen
import com.fgr.adik.ui.screen.auth.register.RegisterScreen
import com.fgr.adik.ui.screen.auth.reset_password.ForgotPasswordScreen
import com.fgr.adik.ui.screen.dashboard.account.change_password.ChangePasswordScreen
import com.fgr.adik.ui.screen.dashboard.account.edit_profile_information.EditProfileInformationScreen
import com.fgr.adik.ui.screen.dashboard.base.BaseScreen
import com.fgr.adik.ui.screen.dashboard.office.create_office.CreateOfficeScreen
import com.fgr.adik.ui.screen.dashboard.office.dashboard_office.DashboardOfficeScreen
import com.fgr.adik.ui.screen.dashboard.office.dashboard_office.exit_office.ExitOfficeScreen

@Composable
fun NavGraph(
    navHostController: NavHostController
) {
    NavHost(
        navController = navHostController,
        route = NavRoute.ROOT.route,
        startDestination = NavRoute.OnBoardingScreen.route
    ) {
        composable(
            route = NavRoute.OnBoardingScreen.route,
        ) {
            OnBoardingScreen(navHostController = navHostController)
        }
        composable(
            route = NavRoute.RegisterScreen.route,
        ) {
            RegisterScreen(navHostController = navHostController)
        }
        composable(
            route = NavRoute.LoginScreen.route,
        ) {
            LoginScreen(navHostController = navHostController)
        }
        composable(
            route = NavRoute.ForgotPasswordScreen.route,
        ) {
            ForgotPasswordScreen(navHostController = navHostController)
        }
        composable(
            route = NavRoute.EmailVerificationScreen.route,
        ) {
            EmailVerificationScreen(navHostController = navHostController)
        }
        composable(
            route = NavRoute.DashboardScreen.route,
        ) {
            BaseScreen(navHostController = navHostController)
        }
        composable(
            route = NavRoute.EditProfileInformationScreen.route
        ) {
            EditProfileInformationScreen(navHostController = navHostController)
        }
        composable(
            route = NavRoute.ChangePasswordScreen.route
        ) {
            ChangePasswordScreen(navHostController = navHostController)
        }
        composable(
            route = NavRoute.CreateOfficeScreen.route
        ) {
            CreateOfficeScreen(navHostController = navHostController)
        }
        composable(
            route = NavRoute.DashboardOfficeScreen.route,
            arguments = listOf(
                navArgument(A_ARGS_KEY) {
                    type = NavType.StringType
                }
            )
        ) {
            val getA = it.arguments?.getString(A_ARGS_KEY)
            DashboardOfficeScreen(navHostController = navHostController, getA)
        }
        composable(
            route = NavRoute.ExitOfficeScreen.route,
            arguments = listOf(
                navArgument(A_ARGS_KEY) {
                    type = NavType.StringType
                }
            )
        ) {
            val getA = it.arguments?.getString(A_ARGS_KEY)
            ExitOfficeScreen(navHostController = navHostController, getA)
        }
    }
}