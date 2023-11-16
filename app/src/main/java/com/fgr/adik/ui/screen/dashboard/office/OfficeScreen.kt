package com.fgr.adik.ui.screen.dashboard.office

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.fgr.adik.component.button.ButtonPrimary
import com.fgr.adik.component.button.ButtonSecondary
import com.fgr.adik.component.item.ItemOfficeScreen
import com.fgr.adik.component.navbar.NavBarPrimary
import com.fgr.adik.component.z9_others.HorizontalDiv
import com.fgr.adik.ui.theme.ADIKTheme

@Composable
fun OfficeScreen(
    navHostController: NavHostController,
    myPaddingValues: PaddingValues,
    contentRoute: MutableState<Int>
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
                modifier = Modifier
                    .fillMaxWidth()
                    .background(colorScheme.surface)
                    .padding(24.dp)
            ) {
                Text(
                    text = "Tambah Kantor",
                    style = MaterialTheme.typography.titleSmall,
                    color = colorScheme.onSurface
                )
                Spacer(modifier = Modifier.padding(top = 4.dp))
                Text(
                    text = "Anda dapat bergabung dengan kantor yang sudah ada atau membuat kantor Anda sendiri.",
                    style = MaterialTheme.typography.bodySmall,
                    color = colorScheme.onSurface
                )
                Spacer(modifier = Modifier.padding(top = 24.dp))
                Row(
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    ButtonSecondary(
                        modifier = Modifier
                            .weight(0.8f),
                        text = "Buat"
                    )
                    ButtonPrimary(
                        modifier = Modifier
                            .weight(0.8f),
                        text = "Gabung"
                    )
                    Spacer(modifier = Modifier.weight(1f))
                }
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
                    text = "Kantor",
                    style = MaterialTheme.typography.titleSmall
                )
                Text(
                    text = "Daftar kantor Anda.",
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
        items(1) {
            Column {
                HorizontalDiv()
                val url =
                    "https://online.visual-paradigm.com/repository/images/24c5981d-4a75-4080-b617-d77a65aa4a1f/logos-design/furniture-logo-designed-for-interior-design-company.png"
                ItemOfficeScreen(
                    officeImageUrl = url,
                    officeName = "PT. Kursi Terbang Perawan",
                    access = "Pengelola Utama",
                    division = "CEO"
                ) {
                    // ON ITEM CLICKED
                }
            }
        }
    }
}

@SuppressLint("UnrememberedMutableState")
@Preview
@Composable
fun OfficeScreenPreview() {
    ADIKTheme {
        OfficeScreen(
            navHostController = rememberNavController(),
            myPaddingValues = PaddingValues(bottom = 48.dp),
            contentRoute = mutableIntStateOf(1)
        )
    }
}