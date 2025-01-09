package com.example.hci_markets.presentation.screen.selectMarketScreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hci_markets.presentation.ui.theme.HCIMarketsTheme

@Composable
fun CheckableItem(
    title: String,
    selected: Boolean = false,
    modifier: Modifier = Modifier,
    onClick: (Boolean) -> Unit = {}
){
    Row(modifier.clickable {
        onClick.invoke(!selected)
    }){
        Text(
            modifier = Modifier.weight(1f).align(Alignment.CenterVertically).padding(start = 8.dp),
            text = title,
        )
        Checkbox(
            checked = selected,
            onCheckedChange = onClick
        )
    }
}

@Preview
@Composable
private fun Preview(){
    HCIMarketsTheme {
        CheckableItem(
            "Norwich market"
        )
    }
}