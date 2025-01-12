package com.example.hci_markets.presentation.screen.homeScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.example.hci_markets.presentation.ui.common.NewsCard
import com.example.hci_markets.presentation.ui.theme.HCIMarketsTheme

@Composable
fun HomeScreen(
    newsItems: List<NewsItem>,
    marketItems: List<MarketItem>,
    navToMarkets: () -> Unit = {},
    modifier: Modifier = Modifier
){
    Surface {
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.MailOutline,
                        contentDescription = null
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = stringResource(id = R.string.recent_news),
                        style = MaterialTheme.typography.headlineMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
            }

            item {
                LazyRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(newsItems.size) { newsItem ->
                        NewsCard(
                            title = newsItems[newsItem].title,
                            location = newsItems[newsItem].location,
                            image = painterResource(newsItems[newsItem].image),
                            description = newsItems[newsItem].description,
                            url = newsItems[newsItem].url,
                            inColumn = false,
                        )
                    }
                }
            }

            item {
                Divider(Modifier.padding(top = 8.dp))
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.ShoppingCart,
                        contentDescription = null
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = stringResource(id = R.string.market_activity),
                        style = MaterialTheme.typography.headlineMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
            }

            items(marketItems.size) { i ->
                Spacer(modifier = Modifier.padding(2.dp))
                BusyCard(
                    title = marketItems[i].name,
                    busyness = marketItems[i].busyness,
                    onClick = navToMarkets,
                )
            }
        }
    }
}

@Preview
@Composable
private fun Preview(){
    val recentNews = listOf(
        NewsItem(
            title = "New pop-up shop now open",
            location = "Norwich Market",
            image = R.drawable.testnewsimage,
            description = "A new comic book pop-up has opened in Norwich Market. Paul Dunne founded it...",
            url = "https://example.com/news1"
        ),
        NewsItem(
            title = "New event this weekend",
            location = "City Park",
            image = R.drawable.testnewsimage,
            description = "Join us this weekend for a free community event in City Park...",
            url = "https://example.com/news2"
        )
    )

    val marketItems = createMarketItems()

    HCIMarketsTheme {
        HomeScreen(
            newsItems = recentNews,
            marketItems = marketItems
        )
    }
}