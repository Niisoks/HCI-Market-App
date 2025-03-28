package com.example.hci_markets.presentation.screen.selectMarketScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hci_markets.R
import com.example.hci_markets.domain.model.MarketItem
import com.example.hci_markets.domain.model.createMarketItems
import com.example.hci_markets.presentation.ui.theme.HCIMarketsTheme
import java.util.UUID

@Composable
fun SelectMarketsListScreen(
    markets: List<MarketItem>,
    selectedMarkets: List<UUID> = listOf(),
    onMarketClick: (UUID) -> Unit = {},
    searchText: String = "",
    onSearchTextChanged: (String) -> Unit = {},
    visibleMarkets: List<UUID> = listOf(),
){
    Column {
        Text(
            stringResource(R.string.please_select_market),
            modifier = Modifier.padding(12.dp)
        )
        SearchBarContainer(
            currentText = searchText,
            onTextChange = onSearchTextChanged
        ) {
            CheckableLazyList(
                markets.filter { if(visibleMarkets.isNotEmpty()) visibleMarkets.contains(it.uid) else true },
                selectedMarkets,
                onMarketClick
            )
        }
    }
}

@Preview
@Composable
private fun Preview(){
    val x = UUID.randomUUID()
    val y = UUID.randomUUID()
    HCIMarketsTheme {
        SelectMarketsListScreen(
            createMarketItems(),
            listOf(x, y),
        )
    }
}