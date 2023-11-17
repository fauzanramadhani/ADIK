package com.fgr.adik.ui.screen.dashboard.account

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.fgr.adik.R
import com.fgr.adik.component.button.ButtonTextIcon
import com.fgr.adik.component.navbar.NavBarPrimary
import com.fgr.adik.component.z9_others.HorizontalDiv
import com.fgr.adik.component.z9_others.LoadImageUrl
import com.fgr.adik.component_core.icon.BaseCircleIconBox
import com.fgr.adik.ui.theme.ADIKTheme

@Composable
fun AccountScreen(
    navHostController: NavHostController,
    myPaddingValues: PaddingValues,
    contentRoute: MutableState<Int>,
//    accountScreenViewModel: AccountScreenViewModel = hiltViewModel() ,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier
            .background(colorScheme.background)
            .fillMaxSize()
    ) {
        NavBarPrimary() {
            // TODO: On support button clicked
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(colorScheme.surface)
        ) {
            Text(
                text = "Profil",
                style = typography.labelLarge,
                color = colorScheme.onSurface,
                modifier = Modifier
                    .padding(24.dp)
            )
            HorizontalDiv()
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp),
                modifier = Modifier
                    .padding(24.dp)
            ) {
                Box {
                    LoadImageUrl(
                        url = "https://adik-api.neodigitalcreation.my.id/public/images/default/default.jpeg",
                        modifier = Modifier
                            .size(90.dp)
                            .clip(CircleShape)
                    )
                    Box(
                        modifier = Modifier
                            .background(colorScheme.surface, shape = CircleShape)
                            .padding(4.dp)
                            .align(Alignment.BottomEnd),
                    ) {
                        BaseCircleIconBox(
                            enabled = true,
                            onClick = {
                                // TODO: On edit profile icon clicked
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.Edit,
                                contentDescription = null,
                                tint = colorScheme.onPrimary,
                                modifier = Modifier
                                    .align(Alignment.Center)
                            )
                        }
                    }
                }
                Text(
                    text = "Hardiyanto Tukono El Mardono",
                    style = typography.bodyMedium,
                    color = colorScheme.onSurface,
                    modifier = Modifier
                        .padding(top = 8.dp)
                )
                Text(
                    text = "hardiyanto@gmail.com",
                    style = typography.bodySmall,
                    color = colorScheme.secondary,
                )
                Text(
                    text = "082129230534",
                    style = typography.bodySmall,
                    color = colorScheme.secondary,
                )
            }
            HorizontalDiv()
            // Furlough request button
            ButtonTextIcon(
                icon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_furlough),
                        contentDescription = null,
                        tint = colorScheme.onPrimary,
                        modifier = Modifier
                            .fillMaxSize()
                            .align(Alignment.Center)
                    )
                },
                title = "Pengajuan Gabung Kantor"
            ) {

            }
            HorizontalDiv()
            // Edit profile information button
            ButtonTextIcon(
                icon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_edit_profile_information),
                        contentDescription = null,
                        tint = colorScheme.onPrimary,
                        modifier = Modifier
                            .fillMaxSize()
                            .align(Alignment.Center)
                    )
                },
                title = "Ubah Informasi Profil"
            ) {

            }
            HorizontalDiv()
            // Change password button
            ButtonTextIcon(
                icon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_lock),
                        contentDescription = null,
                        tint = colorScheme.onPrimary,
                        modifier = Modifier
                            .fillMaxSize()
                            .align(Alignment.Center)
                    )
                },
                title = "Ubah Sandi"
            ) {

            }
            HorizontalDiv()
            // Logout button
            ButtonTextIcon(
                icon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_logout),
                        contentDescription = null,
                        tint = colorScheme.onPrimary,
                        modifier = Modifier
                            .fillMaxSize()
                            .align(Alignment.Center)
                    )
                },
                title = "Keluar",
                iconBackgroundColor = colorScheme.error,
                textColor = colorScheme.error,
                arrowColor = colorScheme.error
            ) {

            }
        }
    }
}

@SuppressLint("UnrememberedMutableState")
@Preview
@Composable
fun AccountScreenPreview() {
    ADIKTheme {
        AccountScreen(
            navHostController = rememberNavController(),
            myPaddingValues = PaddingValues(bottom = 48.dp),
            contentRoute = mutableIntStateOf(0)
        )
    }
}