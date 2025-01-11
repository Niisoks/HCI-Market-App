package com.example.hci_markets.presentation.screen.newsScreen

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.hci_markets.domain.model.MarketItem
import java.util.UUID


@Composable
fun TagRow(
    items: List<MarketItem>,
    onClick: (String, Boolean) -> Unit
) {
    val selectedItems = remember { mutableStateListOf(*items.map { true }.toTypedArray()) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .horizontalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items.forEachIndexed { index, item ->
            TagChip(
                label = item.name,
                isSelected = selectedItems[index],
                onSelectedChange = {
                    onClick.invoke(item.name, selectedItems[index])
                    selectedItems[index] = !selectedItems[index]
                }
            )
        }
    }
}