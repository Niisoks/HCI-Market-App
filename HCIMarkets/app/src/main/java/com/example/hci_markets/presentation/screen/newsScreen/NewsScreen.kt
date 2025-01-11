package com.example.hci_markets.presentation.screen.newsScreen

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hci_markets.R
import com.example.hci_markets.domain.model.MarketItem
import com.example.hci_markets.domain.model.NewsItem
import com.example.hci_markets.domain.model.createMarketItems
import com.example.hci_markets.domain.model.createNewsItems
import com.example.hci_markets.presentation.screen.homeScreen.BusyCard
import com.example.hci_markets.presentation.ui.common.NewsCard
import com.example.hci_markets.presentation.ui.theme.HCIMarketsTheme

@Composable
fun NewsScreen(
    newsItems: List<NewsItem> = createNewsItems(),
    markets: List<MarketItem> = createMarketItems()
){
    val x = remember{ mutableStateListOf(true, true, true) }
    Surface(Modifier.fillMaxSize()) {
        LazyColumn(Modifier.fillMaxWidth().padding(horizontal = 12.dp)) {
            item {
                Spacer(modifier = Modifier.height(16.dp))
                TagRow(
                    markets,
                ) { name, b ->
                    x[
                        when (name) {
                            "Norwich Market" -> 0
                            "Worstead Estate Farmers Market" -> 1
                            "Sheringham Market" -> 2
                            else -> 0
                        }
                    ] = !b
                }
                Spacer(modifier = Modifier.height(8.dp))
            }

            items(newsItems.size) { newsItem ->
                if(
                    (newsItems[newsItem].location == "Norwich Market" && x[0]) ||
                    (newsItems[newsItem].location == "Worstead Estate Farmers Market" && x[1]) ||
                    (newsItems[newsItem].location == "Sheringham Market" && x[2])
                ) {
                    NewsCard(
                        title = newsItems[newsItem].title,
                        location = newsItems[newsItem].location,
                        image = painterResource(newsItems[newsItem].image),
                        description = newsItems[newsItem].description,
                        url = newsItems[newsItem].url,
                        inColumn = true,
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
        NewsScreen()
    }
}
