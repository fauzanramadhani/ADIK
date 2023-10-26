package com.fgr.adik.ui.screen.auth.register

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.fgr.adik.ui.theme.ADIKTheme

@Composable
fun RegisterScreen(
    navHostController: NavHostController,
) {
    
}

@Preview
@Composable
fun RegisterScreenPrev() {
    ADIKTheme {
        RegisterScreen(rememberNavController())
    }
}