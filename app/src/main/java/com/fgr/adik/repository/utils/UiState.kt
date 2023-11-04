package com.fgr.adik.repository.utils

sealed class UiState<out T: Any?> {

    object Empty : UiState<Nothing>()

    object Loading : UiState<Nothing>()

    data class Success<out T: Any>(val data: T) : UiState<T>()

    data class Error(val errorMessage: String) : UiState<Nothing>()
}