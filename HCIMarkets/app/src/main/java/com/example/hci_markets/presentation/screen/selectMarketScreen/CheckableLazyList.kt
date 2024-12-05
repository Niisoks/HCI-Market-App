package com.example.hci_markets.presentation.screen.selectMarketScreen

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
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

        }
    }
}

@Preview
@Composable
private fun Preview(){
    HCIMarketsTheme {
        CheckableLazyList(
            listOf()
        )
    }
}