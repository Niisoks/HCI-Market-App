package com.example.hci_markets.presentation.screen.marketsScreen.shopItem

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hci_markets.R
import com.example.hci_markets.presentation.screen.marketsScreen.PeakTimes

@Composable
fun IconLabelled(
    icon: Int,
    string: String,
    modifier: Modifier = Modifier
){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Image(
            modifier = Modifier.size(32.dp),
            painter = painterResource(id = icon),
            contentDescription = null,
        )
        Text(
            string,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.labelLarge
        )
    }
}

@Preview
@Composable
private fun Preview(){
    IconLabelled(
        R.drawable.baseline_attach_money_24,
        "Accepts Cash"
    )
}