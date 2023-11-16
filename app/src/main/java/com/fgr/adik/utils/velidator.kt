package com.fgr.adik.utils

fun String.isEmailInvalid(): Boolean {
    val emailRegex = Regex("^[A-Za-z0-9+_.-]+@(.+)$")
    return !(emailRegex.matches(this))
}

fun String.isPhoneNumberInvalid(): Boolean {
    val regex = Regex("^08[0-9]{9,11}$")
    return !regex.matches(this)
}