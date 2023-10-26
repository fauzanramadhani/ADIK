package com.fgr.adik.utils

import androidx.navigation.NavHostController
import com.fgr.adik.navigation.NavRoute

fun NavHostController.navigateSingle(destination: NavRoute) {
    this.navigate(destination.route) {
        launchSingleTop = true
    }
}