package com.fgr.adik.navigation

const val A_ARGS_KEY = "a"
const val B_ARGS_KEY = "b"
const val C_ARGS_KEY = "c"
const val D_ARGS_KEY = "d"
const val E_ARGS_KEY = "e"

sealed class NavRoute (val route: String) {
    object ROOT: NavRoute(route = "root")
    object OnBoardingScreen: NavRoute("on_boarding_screen")
}