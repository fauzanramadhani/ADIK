package com.fgr.adik.data.listener

interface ApiListener1<T : Any> {
    fun onSuccess(data1: T)
    fun onFailure(message1: String)
}

interface ApiListener2<T : Any> {
    fun onSuccess(data2: T)
    fun onFailure(message2: String)
}

interface ApiListener3<T : Any> {
    fun onSuccess(data3: T)
    fun onFailure(message3: String)
}