package com.fgr.adik.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

fun Long.toIso(): String {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
    dateFormat.timeZone = TimeZone.getTimeZone("UTC")
    val date = Date(this)
    return dateFormat.format(date)
}


data class FormattedDate(val date: String, val zone: String)

object DatePattern {
    const val HOUR = "HH:mm"
    const val DAY = "dd"
    const val MONTH = "MMMM"
    const val YEAR = "yyyy"
}

fun String.toFormatDateWithZone(pattern: String): String {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
    val date = inputFormat.parse(this)
    val outputFormat = SimpleDateFormat(pattern, Locale.getDefault())
    return outputFormat.format(date)
}

fun getLocalTImeZone(): String = when (TimeZone.getDefault().id) {
    "Asia/Jakarta" -> "WIB"
    "Asia/Makassar" -> "WITA"
    "Asia/Jayapura" -> "WIT"
    else -> "WIB"
}