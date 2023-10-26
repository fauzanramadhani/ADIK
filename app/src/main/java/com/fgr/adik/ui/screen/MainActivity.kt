package com.fgr.adik.ui.screen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.fgr.adik.navigation.NavGraph
import com.fgr.adik.ui.theme.ADIKTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ADIKTheme {
                val navHostController = rememberNavController()
                NavGraph(navHostController = navHostController)
            }
        }
    }
}

@Composable
fun TestTheme() {
    Column(
        verticalArrangement = Arrangement.spacedBy(4.dp),
        modifier = Modifier
            .padding(12.dp)
    ) {
        Text(
            text = "Display Large",
            style = MaterialTheme.typography.displayLarge
        )
        Text(
            text = "Display Medium",
            style = MaterialTheme.typography.displayMedium
        )
        Text(
            text = "Display Small",
            style = MaterialTheme.typography.displaySmall
        )
        Text(
            text = "Headline Large",
            style = MaterialTheme.typography.headlineLarge
        )
        Text(
            text = "Headline Medium",
            style = MaterialTheme.typography.headlineMedium
        )
        Text(
            text = "Headline Small",
            style = MaterialTheme.typography.headlineSmall
        )
        Text(
            text = "Title Large",
            style = MaterialTheme.typography.titleLarge
        )
        Text(
            text = "Title Medium",
            style = MaterialTheme.typography.titleMedium
        )
        Text(
            text = "Title Small",
            style = MaterialTheme.typography.titleSmall
        )
        Text(
            text = "Body Large",
            style = MaterialTheme.typography.bodyLarge
        )
        Text(
            text = "Body Medium",
            style = MaterialTheme.typography.bodyMedium
        )
        Text(
            text = "Body Small",
            style = MaterialTheme.typography.bodySmall
        )
        Text(
            text = "Label Large",
            style = MaterialTheme.typography.bodyLarge
        )
        Text(
            text = "Label Medium",
            style = MaterialTheme.typography.bodyMedium
        )
        Text(
            text = "Label Small",
            style = MaterialTheme.typography.bodySmall
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ADIKTheme {
        TestTheme()
    }
}