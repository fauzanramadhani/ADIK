package com.fgr.adik.ui.screen.dashboard.office.create_office

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.fgr.adik.R
import com.fgr.adik.component.button.ButtonPrimary
import com.fgr.adik.component.button.ButtonTextIconSmall
import com.fgr.adik.component.navbar.NavBarSecondary
import com.fgr.adik.component.text_field.TextFieldFlat
import com.fgr.adik.component.text_field.TextFieldPrimary
import com.fgr.adik.component.warning.WarningHigh
import com.fgr.adik.component.z9_others.DialogLoading
import com.fgr.adik.component.z9_others.HorizontalDiv
import com.fgr.adik.data.request_body.ReqCreateOffice
import com.fgr.adik.navigation.NavRoute
import com.fgr.adik.ui.theme.ADIKTheme
import com.fgr.adik.utils.Toast
import com.fgr.adik.utils.toBitmap

@Composable
fun CreateOfficeScreen(
    navHostController: NavHostController,
    createOfficeViewModel: CreateOfficeViewModel = hiltViewModel(),
) {
    val context = LocalContext.current
    val defaultOfficeImage = R.drawable.office_default_image.toBitmap(context = context)
    // Office Image
    var stateOfficeImageUri by remember {
        mutableStateOf<Uri?>(null)
    }
    // Office Name
    var stateOfficeNameText by rememberSaveable {
        mutableStateOf("")
    }
    var stateOfficeNameError by rememberSaveable {
        mutableStateOf(false)
    }
    var stateOfficeNameErrorText by rememberSaveable {
        mutableStateOf("")
    }
    // Office Address
    var stateOfficeAddressText by rememberSaveable {
        mutableStateOf("")
    }
    var stateOfficeAddressError by rememberSaveable {
        mutableStateOf(false)
    }
    var stateOfficeAddressErrorText by rememberSaveable {
        mutableStateOf("")
    }
    val stateOfficeDivisionList = remember {
        mutableStateListOf<String>()
    }
    var stateCreateOfficeButtonEnabled by rememberSaveable {
        mutableStateOf(true)
    }
    var loadingState by rememberSaveable {
        mutableStateOf(false)
    }
    if (loadingState) {
        DialogLoading()
    }
    val pickImageLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            if (uri != null) {
                // Image selected successfully from gallery, proceed to sending to API
                stateOfficeImageUri = uri
            }
        }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(colorScheme.background)
    ) {
        item {
            NavBarSecondary(
                title = "Buat Kantor"
            ) {
                // TODO: On support button clicked
            }
        }
        item {
            Spacer(modifier = Modifier.padding(bottom = 12.dp))
        }
        item {
            Column(
                verticalArrangement = Arrangement.spacedBy(24.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .background(colorScheme.surface)
                    .padding(vertical = 24.dp)
            ) {
                Text(
                    text = "Informasi Kantor",
                    style = typography.labelLarge,
                    color = colorScheme.onSurface,
                    modifier = Modifier
                        .padding(horizontal = 24.dp)
                )
                HorizontalDiv()
                Column(
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier
                        .padding(horizontal = 24.dp)
                ) {
                    Text(
                        text = "Nama kantor atau perusahaan",
                        style = typography.labelLarge,
                        color = colorScheme.onSurface,
                    )
                    TextFieldPrimary(
                        label = "Contoh: PT. Angkasa Pura Delima",
                        value = stateOfficeNameText,
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Next,
                            keyboardType = KeyboardType.Text
                        ),
                        onValueChange = { value ->
                            if (value.length <= 64) {
                                stateOfficeNameText = value
                                stateOfficeNameError = false
                            } else {
                                stateOfficeNameError = true
                                stateOfficeNameErrorText =
                                    context.getString(
                                        R.string.supporting_text_error_max_char,
                                        "150"
                                    )
                            }
                        },
                        error = stateOfficeNameError,
                        errorText = stateOfficeNameErrorText,
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                }
                Column(
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier
                        .padding(horizontal = 24.dp)
                ) {
                    Text(
                        text = "Nama kantor atau perusahaan",
                        style = typography.labelLarge,
                        color = colorScheme.onSurface,
                    )
                    Box(
                        modifier = Modifier
                            .size(84.dp)
                            .clip(RoundedCornerShape(4.dp))
                            .border(
                                shape = RoundedCornerShape(4.dp),
                                width = 1.dp,
                                color = colorScheme.outline
                            )
                    ) {
                        Image(
                            bitmap = stateOfficeImageUri?.toBitmap(context) ?: defaultOfficeImage,
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillMaxSize()
                        )
                    }
                    ButtonTextIconSmall(
                        icon = {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_upload),
                                contentDescription = null,
                                tint = colorScheme.onPrimary,
                                modifier = Modifier
                                    .fillMaxSize()
                                    .align(Alignment.Center)
                            )
                        },
                        title = "Upload"
                    ) {
                        pickImageLauncher.launch("image/*")
                    }
                }
                Column(
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier
                        .padding(horizontal = 24.dp)
                ) {
                    Text(
                        text = "Alamat kantor atau perusahaan",
                        style = typography.labelLarge,
                        color = colorScheme.onSurface,
                    )
                    TextFieldPrimary(
                        label = "Contoh: JL. Sudan, Kec. Padjajaran",
                        value = stateOfficeAddressText,
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Done,
                            keyboardType = KeyboardType.Text
                        ),
                        onValueChange = { value ->
                            if (value.length <= 64) {
                                stateOfficeAddressText = value
                                stateOfficeAddressError = false
                            } else {
                                stateOfficeAddressError = true
                                stateOfficeNameErrorText =
                                    context.getString(
                                        R.string.supporting_text_error_max_char,
                                        "150"
                                    )
                            }
                        },
                        error = stateOfficeAddressError,
                        errorText = stateOfficeAddressErrorText,
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                }
            }
        }
        item {
            Spacer(modifier = Modifier.padding(bottom = 12.dp))
        }
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(colorScheme.surface)
            ) {
                Text(
                    text = "Divisi",
                    style = typography.labelLarge,
                    color = colorScheme.onSurface,
                    modifier = Modifier
                        .padding(24.dp)
                )
                if (stateOfficeDivisionList.isEmpty()) {
                    WarningHigh(
                        message = "Anda perlu menambahkan minimal 1 divisi",
                        modifier = Modifier
                            .padding(start = 24.dp, end = 24.dp, bottom = 24.dp)
                    )
                }
                HorizontalDiv()
            }
        }
        itemsIndexed(stateOfficeDivisionList) { index, item ->
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .background(colorScheme.surface)
                    .padding(start = 24.dp, end = 24.dp, top = 12.dp)
            ) {
                Text(
                    text = "${index + 1}.",
                    style = typography.labelLarge,
                )
                TextFieldFlat(
                    value = stateOfficeDivisionList[index],
                    onValueChange = { value ->
                        stateOfficeDivisionList[index] = value
                    },
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Done,
                        keyboardType = KeyboardType.Text
                    ),
                    modifier = Modifier
                        .weight(1f)
                )
                IconButton(
                    onClick = {
                        stateOfficeDivisionList.removeAt(index)
                    },
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Close,
                        contentDescription = null,
                        tint = colorScheme.error
                    )
                }
            }
        }
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(colorScheme.surface)
            ) {
                if (stateOfficeDivisionList.isNotEmpty()) {
                    HorizontalDiv(
                        modifier = Modifier
                            .padding(top = 24.dp)
                    )
                }
                ButtonTextIconSmall(
                    icon = {
                        Icon(
                            imageVector = Icons.Rounded.Add,
                            contentDescription = null,
                            tint = colorScheme.onPrimary,
                            modifier = Modifier
                                .fillMaxSize()
                                .align(Alignment.Center)
                        )
                    },
                    paddingBackground = PaddingValues(0.dp),
                    title = "Tambahkan Divisi",
                    modifier = Modifier
                        .padding(24.dp)
                ) {
                    stateOfficeDivisionList.add("")
                }
            }
        }
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(colorScheme.surface)
                    .padding(24.dp)
            ) {
                ButtonPrimary(
                    modifier = Modifier
                        .fillMaxWidth(),
                    enabled = stateOfficeNameText.isNotEmpty() && stateOfficeImageUri != null && stateOfficeAddressText.isNotEmpty()
                            && !(stateOfficeDivisionList.any { it == "" }) && stateCreateOfficeButtonEnabled && stateOfficeDivisionList.isNotEmpty(),
                    text = "Buat Kantor",
                ) {
                    loadingState = true
                    stateCreateOfficeButtonEnabled = false
                    createOfficeViewModel.createOffice(
                        reqCreateOffice = ReqCreateOffice(
                            name = stateOfficeNameText,
                            address = stateOfficeAddressText,
                            division = stateOfficeDivisionList
                        ),
                        officeImageUri = stateOfficeImageUri!!
                    ) { success, data, message ->
                        when (message) {
                            "201" -> {
                                Toast(context, "Berhasil membuat kantor").long()
                            }

                            "404" -> Toast(context, "Kantor tidak ditemukan").long()
                            else -> {
                                Toast(context, message).long()
                            }
                        }
                        if (success) {
                            navHostController.navigate(
                                NavRoute.DashboardOfficeScreen.navigateWithId(
                                    data!!.officeId!!
                                )
                            )
                        }
                        loadingState = false
                        stateCreateOfficeButtonEnabled = true
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun CreateOfficeScreenPreview() {
    ADIKTheme {
        CreateOfficeScreen(rememberNavController())
    }
}