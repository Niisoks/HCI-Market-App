package com.example.hci_markets.presentation.screen.settingsScreen

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.palette.graphics.Palette
import com.example.hci_markets.presentation.nav.Screen
import com.example.hci_markets.presentation.ui.theme.HCIMarketsTheme

@Composable
fun SettingsScreen(navController: NavController) {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        SettingsOption(icon = Icons.Default.MoreVert, title = "Theme"){
            Toast.makeText(context, "Currently not implemented!", Toast.LENGTH_SHORT).show()
        }
        SettingsOption(icon = Icons.Default.List, title = "Tasks"){
            navController.navigate(Screen.Tasks.route)
        }
        SettingsOption(icon = Icons.Default.Phone, title = "Help and Support"){
            emailIntent(context)
        }
        SettingsOption(icon = Icons.Default.Home, title = "Set Home Location"){
            navController.navigate(Screen.SelectHome.route)
        }
        SettingsOption(icon = Icons.Default.ShoppingCart, title = "Select Markets"){
            navController.navigate(Screen.SelectMarket.route)
        }
        SettingsOption(icon = Icons.Default.Settings, title = "Accessibility Options"){
            Toast.makeText(context, "Currently not implemented!", Toast.LENGTH_SHORT).show()
        }

        Spacer(modifier = Modifier.weight(1f))

        Text(
            text = "Something not right? Need us to add a business or change some information?",
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
        )
        Text(
            text = "Email us at norwichfake@email.com with your request and we will fix it as soon as possible!",
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )

        // Email Button
        Button(
            onClick = { emailIntent(context)  },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(8.dp)
        ) {
            Text(text = "Send Email")
        }
    }
}

fun emailIntent(context: Context){
    val intent = Intent(Intent.ACTION_SENDTO).apply {
        data = Uri.parse("mailto:")
        putExtra(Intent.EXTRA_EMAIL, arrayOf("norwichfake@email.com"))
        putExtra(Intent.EXTRA_SUBJECT, "Request for Change or Addition")
        putExtra(Intent.EXTRA_TEXT, "Hi, I would like to request the following changes: \n\n")
    }

    if (intent.resolveActivity(context.packageManager) != null) {
        context.startActivity(intent)
    } else {
        Toast.makeText(context, "No email app installed!", Toast.LENGTH_SHORT).show()
    }
}

@Composable
fun SettingsOption(icon: ImageVector, title: String, onClick: () -> Unit = {}) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick.invoke() }
            .padding(vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = title,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.weight(1f)
        )
        Icon(
            imageVector = Icons.Default.KeyboardArrowRight,
            contentDescription = "Arrow",
            modifier = Modifier.size(24.dp)
        )
    }
}

@Preview
@Composable
private fun Preview(){
    val context = LocalContext.current
    HCIMarketsTheme {
        SettingsScreen(NavController(context))
    }
}