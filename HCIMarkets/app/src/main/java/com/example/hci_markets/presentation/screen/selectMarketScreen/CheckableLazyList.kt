package com.example.hci_markets.presentation.screen.selectMarketScreen

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import com.example.hci_markets.domain.model.MarketItem
import com.example.hci_markets.domain.model.createMarketItems
import com.example.hci_markets.presentation.ui.theme.HCIMarketsTheme
import java.util.UUID

@Composable
fun CheckableLazyList(
    markets: List<MarketItem>,
    selectedMarkets: List<UUID> = listOf(),
    onMarketClick: (UUID) -> Unit = {},
){
    LazyColumn {
        items(
            markets.size,
            { markets[it].uid }
        ){
            val market = markets[it]
            HorizontalDivider()
            val selected = remember(selectedMarkets.size){ selectedMarkets.contains(market.uid) }
            CheckableItem(
                title = market.name,
                selected = selected,
                onClick = {bool ->
                    onMarketClick(market.uid)
                }
            )
        }
    }
}

@Preview
@Composable
private fun Preview(){
    HCIMarketsTheme {
        CheckableLazyList(
            createMarketItems()
        )
    }
}