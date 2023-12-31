package com.fgr.adik.navigation

const val A_ARGS_KEY = "a"
const val B_ARGS_KEY = "b"
const val C_ARGS_KEY = "c"
const val D_ARGS_KEY = "d"
const val E_ARGS_KEY = "e"

sealed class NavRoute (val route: String) {
    object ROOT: NavRoute(route = "root")
    object OnBoardingScreen: NavRoute("on_boarding_screen")
    object RegisterScreen: NavRoute("register_screen")
    object LoginScreen: NavRoute("login_screen")
    object ForgotPasswordScreen: NavRoute("forgot_password_screen")
    object EmailVerificationScreen: NavRoute("email_verification_screen")
    object DashboardScreen: NavRoute ("base_screen")
    object EditProfileInformationScreen: NavRoute ("edit_profile_information_screen")
    object ChangePasswordScreen: NavRoute ("change_password_screen")
    object CreateOfficeScreen: NavRoute ("create_office_screen")
    object DashboardOfficeScreen : NavRoute(route = "dashboard_office_screen/{$A_ARGS_KEY}") {
        fun navigateWithId(
            officeId: String
        ): String {
            return "dashboard_office_screen/$officeId"
        }
    }
    object ExitOfficeScreen : NavRoute(route = "exit_office_screen/{$A_ARGS_KEY}") {
        fun navigateWithId(
            officeId: String
        ): String {
            return "exit_office_screen/$officeId"
        }
    }
}