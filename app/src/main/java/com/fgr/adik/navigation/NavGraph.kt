package com.fgr.adik.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.fgr.adik.ui.screen.auth.login.LoginScreen
import com.fgr.adik.ui.screen.auth.on_boarding.OnBoardingScreen
import com.fgr.adik.ui.screen.auth.register.RegisterScreen

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
    }
}