package com.example.hci_markets.presentation.screen.selectMarketScreen

import FloatingButton
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.SecondaryTabRow
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hci_markets.R
import com.example.hci_markets.domain.model.MarketItem
import com.example.hci_markets.presentation.ui.common.TwoTabNavigation
import com.example.hci_markets.presentation.ui.theme.HCIMarketsTheme
import java.util.UUID

@Composable
fun SelectMarketScreen(
    markets: List<MarketItem> = listOf(),
    selectedMarkets: List<UUID> = listOf(),
    onMarketClick: (UUID) -> Unit = {},
    searchText: String = "",
    onSearchTextChanged: (String) -> Unit = {},
    onSave: () -> Unit = {}
    ){
    Surface(Modifier.fillMaxSize()) {
        Box() {
            TwoTabNavigation(
                tabOneName = stringResource(R.string.list),
                tabTwoName = stringResource(R.string.map),
                tabOneContent = {
                    SelectMarketsListScreen(
                        markets = markets,
                        selectedMarkets = selectedMarkets,
                        onMarketClick = onMarketClick,
                        searchText = searchText,
                        onSearchTextChanged = onSearchTextChanged
                    )
                },
                tabTwoContent = {
                    SelectMarketMap(
                        markets = markets,
                        selectedMarkets = selectedMarkets,
                        onMarketClick = onMarketClick,
                    )
                }
            )
            Column(
                modifier = Modifier.align(Alignment.BottomEnd)
                    .padding(12.dp)
            ) {
                FloatingButton(
                    R.drawable.outline_save_24,
                    onClick = {
                        onSave.invoke()
                    }
                )
            }
        }
    }

}

@Preview
@Composable
private fun Preview(){
    HCIMarketsTheme {
        SelectMarketScreen()
    }
}