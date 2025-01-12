package com.example.hci_markets.presentation.screen.marketsScreen.shopItem

import android.view.MenuItem
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hci_markets.domain.model.RestaurantMenu
import com.example.hci_markets.presentation.ui.theme.HCIMarketsTheme

@Composable
fun MenuDialog(
    menuItems: List<RestaurantMenu>,
    onDismissRequest: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismissRequest,
        text = {
            Column(Modifier.verticalScroll(rememberScrollState())) {
                menuItems.forEach { item ->
                    MenuItemRow(menuItem = item)
                    Divider(modifier = Modifier.padding(vertical = 8.dp))
                }
            }
        },
        confirmButton = {
            TextButton(onClick = onDismissRequest) {
                Text(text = "Close")
            }
        },
        tonalElevation = 50.dp
    )
}

@Composable
fun MenuItemRow(menuItem: RestaurantMenu) {
    Column(modifier = Modifier.padding(8.dp)) {
        Text(
            text = menuItem.title,
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = menuItem.description,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Preview
@Composable
private fun Preview(){
    HCIMarketsTheme {
        MenuDialog(
            listOf(RestaurantMenu(), RestaurantMenu(), RestaurantMenu(),
                RestaurantMenu(), RestaurantMenu(), RestaurantMenu(), RestaurantMenu(), RestaurantMenu(), RestaurantMenu())
        ) { }
    }
}