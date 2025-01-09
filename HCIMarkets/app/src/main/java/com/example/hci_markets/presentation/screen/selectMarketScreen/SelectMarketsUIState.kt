package com.example.hci_markets.presentation.screen.selectMarketScreen

import com.example.hci_markets.domain.model.MarketItem
import java.util.UUID

data class SelectMarketsUIState(
    val markets: List<MarketItem> = listOf(),
    val visibleMarkets: List<UUID> = listOf(),
    val selectedMarkets: List<UUID> = listOf(),
    val text: String = ""
)
