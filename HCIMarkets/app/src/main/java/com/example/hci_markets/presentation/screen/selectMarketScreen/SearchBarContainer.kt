package com.example.hci_markets.presentation.screen.selectMarketScreen

import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hci_markets.R
import com.example.hci_markets.domain.model.MarketItem
import com.example.hci_markets.presentation.ui.theme.HCIMarketsTheme

@Composable
fun SearchBarContainer(
    currentText: String = "",
    onTextChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit = {}
){
    Surface(modifier = modifier) {
        Column {
            Box(modifier = Modifier.padding(12.dp)) {
                TextField(
                    currentText,
                    onTextChange,
                    label = {
                        Text(
                            stringResource(R.string.enter_a_market_name),
                            maxLines = 1,
                            modifier = Modifier.basicMarquee()
                        )
                            },
                    modifier = Modifier.fillMaxWidth(),
                    maxLines = 1
                )
                Icon(
                    painter = painterResource(id = R.drawable.baseline_search_24),
                    contentDescription = null,
                    modifier = Modifier.padding(8.dp).align(Alignment.CenterEnd)
                )
            }
            content.invoke()
        }
    }
}

@Preview
@Composable
private fun Preview(){
    val x = remember{mutableStateOf("")}
    HCIMarketsTheme {
        SearchBarContainer(
            currentText = x.value,
            onTextChange = {string ->
            x.value = string
        }){
            CheckableLazyList(
                markets = listOf(MarketItem(
                    name = "test thing",
                    busyness = 0.8f
                ),
                    MarketItem(
                        name = "test thing",
                        busyness = 0.8f
                    ),
                    MarketItem(
                        name = "test thing",
                        busyness = 0.8f
                    ),
                    MarketItem(
                        name = "test thing",
                        busyness = 0.8f
                    )),
                onMarketClick = {}
            )
        }
    }
}