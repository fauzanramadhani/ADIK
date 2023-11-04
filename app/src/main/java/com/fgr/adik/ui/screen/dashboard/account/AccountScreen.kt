package com.fgr.adik.ui.screen.dashboard.account

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.fgr.adik.component.button.ButtonPrimary
import com.fgr.adik.navigation.NavRoute
import com.fgr.adik.repository.utils.navigateToTop

@Composable
fun AccountScreen(
    navHostController: NavHostController,
    myPaddingValues: PaddingValues,
    contentRoute: MutableState<Int>,
    accountScreenViewModel: AccountScreenViewModel = hiltViewModel() ,
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = "Account Screen",
            style = typography.headlineSmall
        )
        ButtonPrimary(
            text = "Keluar"
        ) {
            accountScreenViewModel.logout()
            navHostController.navigateToTop(NavRoute.OnBoardingScreen)
        }
    }
}