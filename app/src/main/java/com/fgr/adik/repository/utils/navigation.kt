package com.fgr.adik.repository.utils

import androidx.navigation.NavHostController
import com.fgr.adik.navigation.NavRoute

fun NavHostController.navigateToTop(
    destination: NavRoute,
) {
    this.navigate(destination.route) {
        launchSingleTop = true
        popUpTo(destination.route) {
            inclusive = true
        }
    }
}
fun NavHostController.navigateTo(
    destination: NavRoute,
) {
    this.navigate(destination.route) {
        launchSingleTop = true
    }
}

