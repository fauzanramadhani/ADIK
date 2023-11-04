package com.fgr.adik.repository.utils

fun String.isEmailInvalid(): Boolean {
    val emailRegex = Regex("^[A-Za-z0-9+_.-]+@(.+)$")
    return !(emailRegex.matches(this))
}