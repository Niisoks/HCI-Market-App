package com.example.hci_markets.presentation.screen.marketsScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hci_markets.domain.model.MarketItem
import com.example.hci_markets.domain.model.createMarketItems
import com.example.hci_markets.presentation.ui.theme.HCIMarketsTheme

@Composable
fun MarketsScreen(
    markets: List<MarketItem> = createMarketItems(),
    onClickShops: (MarketItem) -> Unit = {}
) {
    Surface(Modifier.fillMaxSize()) {
        LazyColumn(
            Modifier.fillMaxWidth().padding(horizontal = 12.dp).padding(vertical = 12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(markets.size) { marketItem ->
                val item = markets[marketItem]
                MarketInfoCard(
                    name = item.name,
                    location = item.location,
                    openingTimes = item.openingTimes,
                    openingDays = item.openingDays,
                    busyness = item.busyness,
                    peak = item.peak,
                    latLng = item.latLng,
                    onClick = {onClickShops.invoke(item)}
                )
            }
        }
    }
}

@Preview
@Composable
private fun Preview(){
    HCIMarketsTheme {
        MarketsScreen()
    }
}