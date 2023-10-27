package com.fgr.adik.repository.utils

import androidx.navigation.NavHostController
import com.fgr.adik.navigation.NavRoute

fun NavHostController.navigateSingle(
    destination: NavRoute,
    callback: (() -> Unit)? = null
) {
    this.navigate(destination.route) {
        launchSingleTop = true
        popUpTo(destination.route) {
            inclusive = true
        }
    }.also {
        if (callback != null) {
            callback()
        }
    }
}

fun String.isEmailInvalid(): Boolean {
    val emailRegex = Regex("^[A-Za-z0-9+_.-]+@(.+)$")
    return !(emailRegex.matches(this))
}
