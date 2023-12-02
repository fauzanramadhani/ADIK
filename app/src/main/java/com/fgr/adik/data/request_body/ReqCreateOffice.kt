package com.fgr.adik.data.request_body

data class ReqCreateOffice(
    val name: String,
    val address: String,
    val division: List<String>,
)