package com.example.hci_markets.presentation.screen.marketsScreen

import android.view.Surface
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.hci_markets.R
import com.example.hci_markets.domain.model.createMarketItems
import com.example.hci_markets.presentation.ui.common.TwoTabNavigation
import com.example.hci_markets.presentation.ui.theme.HCIMarketsTheme

@Composable
fun MarketShopsScreen(){
    Surface(modifier = Modifier.fillMaxSize()){
        TwoTabNavigation(
            tabOneName = stringResource(R.string.list),
            tabTwoName = stringResource(R.string.map),
            tabOneContent = {
                ShopList(market = createMarketItems()[0])
            },
            tabTwoContent = {
                MarketShopsMap()
            }
        )
    }
}

@Preview
@Composable
private fun Preview(){
    HCIMarketsTheme {
        MarketShopsScreen()
    }
}