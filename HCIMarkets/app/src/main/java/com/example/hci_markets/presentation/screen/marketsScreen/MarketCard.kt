package com.example.hci_markets.presentation.screen.marketsScreen

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hci_markets.R
import com.example.hci_markets.presentation.screen.homeScreen.busynessToGraph
import com.example.hci_markets.presentation.screen.homeScreen.busynessToString
import com.example.hci_markets.presentation.ui.theme.HCIMarketsTheme
import com.google.android.gms.maps.model.LatLng

@Composable
fun MarketInfoCard(
    name: String = "Norwich Market",
    location: String = "Norwich",
    openingTimes: String = "9am - 4pm",
    openingDays: String = "Mon - Sun",
    busyness: Float = 0.5f,
    peak: PeakTimes = PeakTimes.TWELVE,
    latLng: LatLng = LatLng(52.630886, 1.297355),
    onClick: () -> Unit = {},
) {
    val context = LocalContext.current
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = name,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = location,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
                Column(horizontalAlignment = Alignment.End) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            painter = painterResource(R.drawable.baseline_access_time_24),
                            contentDescription = "Opening Hours",
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = openingTimes,
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                    Text(
                        text = openingDays,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Image(
                        painter = painterResource(id = remember{busynessToGraph(busyness)}),
                        contentDescription = null,
                        modifier = Modifier
                            .size(64.dp),
                    )
                    Text(
                        text = stringResource(remember{busynessToString(busynessToGraph(busyness))}),
                        style = MaterialTheme.typography.bodySmall,
                    )
                }
                Image(
                    painter = painterResource(id = remember{
                        when(peak){
                            PeakTimes.NINE -> R.drawable._ambusy
                            PeakTimes.TWELVE -> R.drawable._2pmbusy
                            PeakTimes.THREE -> R.drawable._pmbusy
                    } }),
                    contentDescription = null,
//                    modifier = Modifier
//                        .size(64.dp),
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(onClick = { openLatLngInMaps(latLng, context)}) {
                    Text(text = stringResource(R.string.directions))
                }
                Button(onClick = onClick) {
                    Text(text = stringResource(R.string.shops))
                }
            }
        }
    }
}

@Preview
@Composable
private fun Preview(){
    HCIMarketsTheme {
        MarketInfoCard() { }
    }
}

fun openLatLngInMaps(latlng: LatLng, context: Context){
    val gmmIntentUri = Uri.parse("google.navigation:q=${latlng.latitude},${latlng.longitude}")
    val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri).apply {
        setPackage("com.google.android.apps.maps")
    }
    try {
        context.startActivity(mapIntent)
    } catch (e: Exception) {
        Toast.makeText(context, "Google Maps not installed", Toast.LENGTH_SHORT).show()
    }
}