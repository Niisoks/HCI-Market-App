package com.example.hci_markets.presentation.screen.marketsScreen.shopItem

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hci_markets.R
import com.example.hci_markets.presentation.ui.theme.HCIMarketsTheme

@Composable
fun TwoLineIconColumn(
    icon: Int = R.drawable.baseline_access_time_24,
    line1: String = "12pm - 5pm",
    line2: String = "Mon - Sat"
){
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            painter = painterResource(icon),
            contentDescription = "Opening Hours",
            modifier = Modifier.size(18.dp)
        )
        Spacer(modifier = Modifier.width(4.dp))
        Column(horizontalAlignment = Alignment.Start) {
           Text(
               text = line1,
               style = MaterialTheme.typography.titleSmall
           )
            Text(
                text = line2,
                style = MaterialTheme.typography.titleSmall
            )
        }
    }
}

@Preview
@Composable
private fun Preview(){
    HCIMarketsTheme {
        TwoLineIconColumn()
    }
}