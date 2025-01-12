package com.example.hci_markets.presentation.screen.marketsScreen

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hci_markets.R
import com.example.hci_markets.domain.model.MarketItem
import com.example.hci_markets.domain.model.createMarketItems
import com.example.hci_markets.presentation.screen.marketsScreen.shopItem.ShopItem
import com.example.hci_markets.presentation.screen.selectMarketScreen.SearchBarContainer
import com.example.hci_markets.presentation.ui.theme.HCIMarketsTheme

@Composable
fun ShopList(market: MarketItem) {
    val searchText = remember{ mutableStateOf("")}
    val context = LocalContext.current
    Surface(Modifier.fillMaxSize()) {
        SearchBarContainer(
            currentText = searchText.value,
            onTextChange = {string ->
                searchText.value = string
                Toast.makeText(context, "Search feature not currently implemented", Toast.LENGTH_SHORT).show()
            },
            hint = R.string.search_name_keyword
        ) {
            LazyColumn(
                Modifier.fillMaxWidth().padding(horizontal = 12.dp).padding(vertical = 12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(market.shops.size) { shopIndex ->
                    val shop = market.shops[shopIndex]
                    ShopItem(
                        pfp = shop.profileImage,
                        image = shop.image,
                        name = shop.name,
                        description = shop.description,
                        openingTimes = shop.openingToClosingTime,
                        openingDays = shop.openDays,
                        stall = shop.stallNo.toString(),
                        row = shop.rowLetter,
                        busiestAt = shop.busiest,
                        cash = shop.acceptsCash,
                        card = shop.acceptsCard,
                        vegetarian = shop.vegetarian,
                        vegan = shop.vegan,
                        menu = shop.menu,
                        email = shop.email,
                        phone = shop.phone,
                        website = shop.website
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun Preview(){
    HCIMarketsTheme {
        ShopList(createMarketItems()[0])
    }
}