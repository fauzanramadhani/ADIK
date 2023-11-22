package com.fgr.adik.ui.screen.dashboard.home

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.fgr.adik.R
import com.fgr.adik.component.item.ItemHomeScreen
import com.fgr.adik.component.navbar.NavBarPrimary
import com.fgr.adik.component.z9_others.HorizontalDiv
import com.fgr.adik.ui.theme.ADIKTheme
import com.fgr.adik.utils.DatePattern
import com.fgr.adik.utils.toFormatDateWithZone
import com.fgr.adik.utils.toIso


@Composable
fun HomeScreen(
    navHostController: NavHostController,
    myPaddingValues: PaddingValues,
    contentRoute: MutableState<Int>,
    homeScreenViewModel: HomeScreenViewModel = hiltViewModel()
) {

    LazyColumn(
        modifier = Modifier
            .background(colorScheme.background)
            .padding(myPaddingValues)
    ) {
        item {
            NavBarPrimary() {
                // TODO: On support button clicked
            }
        }
        item {
            Spacer(modifier = Modifier.padding(vertical = 6.dp))
        }
        item {
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .background(colorScheme.surface)
                    .padding(24.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.screen_home_your_absence_today),
                    style = typography.titleSmall
                )
                Text(
                    text = System.currentTimeMillis()
                        .toIso()
                        .toFormatDateWithZone(
                            pattern = "${DatePattern.DAY} ${DatePattern.MONTH} ${DatePattern.YEAR}"
                        ),
                    style = typography.bodySmall
                )
            }
        }
        items(1) {
            Column {
                HorizontalDiv()
                val url =
                    "https://www.freepnglogos.com/uploads/company-logo-png/company-logo-transparent-png-19.png"
                ItemHomeScreen(
                    officeImageUrl = url,
                    officeName = "PT. Kursi Terbang Perawan",
                    entryTime = "08:00 - 09:00",
                    outTime = "16:00 - 17:00",
                    status = "Absen masuk dibuka",
                )
            }
        }
    }
}

@SuppressLint("UnrememberedMutableState")
@Preview
@Composable
fun HomeScreenPreview() {
    ADIKTheme {
        HomeScreen(
            navHostController = rememberNavController(),
            myPaddingValues = PaddingValues(bottom = 48.dp),
            contentRoute = mutableIntStateOf(0)
        )
    }
}