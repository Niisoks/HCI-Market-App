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
import com.example.hci_markets.presentation.ui.theme.HCIMarketsTheme
import java.util.UUID

@Composable
fun SelectMarketsListScreen(
    markets : List<MarketItem>,
    selectedMarkets : List<UUID> = listOf(),
    onMarketClick : (UUID) -> Unit = {},
    searchText: String = "",
    onSearchTextChanged: (String) -> Unit = {},
){
    val x = remember{ mutableStateOf("") }
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
                markets,
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
            listOf(
                MarketItem(
                    uid = x,
                    name = "Norwich Market",
                    busyness = 0.8f
                ),
                MarketItem(
                    name = "Worstead Estate Farmers Market",
                    busyness = 0.6f
                ),
                MarketItem(
                    uid = y,
                    name = "Sheringham Market",
                    busyness = 0.3f
                ),
            ),
            listOf(x, y)
        )
    }
}