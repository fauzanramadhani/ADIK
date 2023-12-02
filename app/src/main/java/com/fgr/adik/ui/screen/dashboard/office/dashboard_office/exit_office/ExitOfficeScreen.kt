package com.fgr.adik.ui.screen.dashboard.office.dashboard_office.exit_office

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.fgr.adik.component.button.ButtonPrimary
import com.fgr.adik.component.navbar.NavBarSecondary
import com.fgr.adik.component.text_field.CustomDropDownMenu
import com.fgr.adik.component.text_field.rememberDropDownStateHolder
import com.fgr.adik.ui.theme.ADIKTheme

@Composable
fun ExitOfficeScreen(
    navHostController: NavHostController,
    officeId: String? = null,
) {
    var switchState by rememberSaveable {
        mutableStateOf(false)
    }
    val dropDownState = rememberDropDownStateHolder(
        currentState = "",
        listItem = listOf(
            "Fauzan",
            "Martis",
            "Kagura",
            "Argus"
        )
    )
    var confirmationButtonState by rememberSaveable {
        mutableStateOf(true)
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(24.dp),
        modifier = Modifier
            .fillMaxSize()
            .background(colorScheme.surface)
            .verticalScroll(rememberScrollState())
    ) {
        NavBarSecondary(
            title = "Keluar Kantor"
        ) {
            // TODO: On Contact Service Clicked
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
        ) {
            Text(
                text = "Hapus Kantor",
                style = typography.labelLarge,
                color = colorScheme.onSurface,
                modifier = Modifier
                    .padding(bottom = 4.dp)
            )
            Text(
                text = "Seluruh informasi kantor terkait absensi, cuti dan yang lainnya akan di hapus secara permanen.",
                style = typography.bodySmall,
                color = colorScheme.onSurface,
                modifier = Modifier
                    .padding(bottom = 12.dp)
            )
            Switch(
                checked = switchState,
                onCheckedChange = {
                    switchState = it
                }
            )
        }
        if (!switchState) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
            ) {
                Text(
                    text = "Alihkan Kepemilikan",
                    style = typography.labelLarge,
                    color = colorScheme.onSurface,
                    modifier = Modifier
                        .padding(bottom = 4.dp)
                )
                Text(
                    text = "Kepemilikan kantor akan di alihkan ke pengurus kantor yang lain.",
                    style = typography.bodySmall,
                    color = colorScheme.onSurface,
                    modifier = Modifier
                        .padding(bottom = 12.dp)
                )
                CustomDropDownMenu(
                    stateHolder = dropDownState,
                    label = "Silahkan Pilih Admin",
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }
        }
        Spacer(modifier = Modifier.weight(1f))
        ButtonPrimary(
            modifier = Modifier
                .padding(24.dp)
                .fillMaxWidth(),
            enabled = confirmationButtonState &&
                    if (!switchState && dropDownState.value.isNotEmpty()) {
                        true
                    } else switchState,
            text = "Konfirmasi",
            colors = ButtonDefaults.filledTonalButtonColors(
                containerColor = colorScheme.error,
                contentColor = colorScheme.onError,
            )
        )
    }
}

@Preview
@Composable
fun ExitOfficeScreenPreview() {
    ADIKTheme {
        ExitOfficeScreen(rememberNavController())
    }
}