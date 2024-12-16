package com.example.hci_markets.presentation.screen.selectMarketScreen

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import com.example.hci_markets.domain.model.MarketItem
import com.example.hci_markets.presentation.ui.theme.HCIMarketsTheme

@Composable
fun CheckableLazyList(
    markets : List<MarketItem>,
){
    LazyColumn {
        items(
            markets.size,
            { markets[it].uid }
        ){
            val market = markets[it]
            HorizontalDivider()
            val selected = remember{ mutableStateOf(false) }
            CheckableItem(
                title = market.name,
                selected = selected.value,
                onClick = {bool ->
                    selected.value = bool
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
            listOf(
                MarketItem(
                    name = "Norwich Market",
                    busyness = 0.8f
                ),
                MarketItem(
                    name = "Worstead Estate Farmers Market",
                    busyness = 0.6f
                ),
                MarketItem(
                    name = "Sheringham Market",
                    busyness = 0.3f
                ),
            )
        )
    }
}