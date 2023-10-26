package com.fgr.adik.ui.screen.auth.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.fgr.adik.R
import com.fgr.adik.component.navbar.NavBarSecondary
import com.fgr.adik.component.text_field.TextFieldPrimary
import com.fgr.adik.ui.theme.ADIKTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    navHostController: NavHostController,
) {
    var emailTextState by remember {
        mutableStateOf("")
    }
    var emailErrorState by remember {
        mutableStateOf(false)
    }
    var emailErrorTextState by remember {
        mutableStateOf("")
    }
    val context = LocalContext.current

    Scaffold(
        topBar = {
            NavBarSecondary(stringResource(id = R.string.login))
        }
    ) { paddingValues ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(24.dp),
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surface)
                .verticalScroll(rememberScrollState())
                .padding(
                    start = 24.dp,
                    end = 24.dp,
                    top = paddingValues.calculateTopPadding() + 24.dp,
                    bottom = 24.dp
                )
        ) {
            Image(
                painter = painterResource(id = R.drawable.login_illustration),
                contentDescription = null,
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = stringResource(id = R.string.screen_login_title),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface,
                textAlign = TextAlign.Center
            )
            TextFieldPrimary(
                label = stringResource(id = R.string.email),
                value = emailTextState,
                onValueChange = { value ->
                    if (value.length < 10) {
                        emailTextState = value
                    }
                },
                error = emailErrorState,
                errorText = emailErrorTextState,
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
    }
}

@Preview
@Composable
fun LoginScreenPreview() {
    ADIKTheme {
        LoginScreen(navHostController = rememberNavController())
    }
}