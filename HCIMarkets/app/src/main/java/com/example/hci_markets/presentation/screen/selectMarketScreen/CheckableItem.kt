package com.example.hci_markets.presentation.screen.selectMarketScreen

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.hci_markets.presentation.ui.theme.HCIMarketsTheme

@Composable
fun CheckableItem(
    title: String,
    selected: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
){
    
}

@Preview
@Composable
private fun Preview(){
    HCIMarketsTheme {
        CheckableItem()
    }
}