package com.example.hci_markets.domain.model

import com.google.android.gms.maps.model.LatLng
import java.util.UUID

data class MarketItem (
    val uid: UUID = UUID.randomUUID(),
    val name: String,
    val latLng: LatLng = LatLng(52.630886, 1.297355),
    val busyness: Float
)

fun createMarketItems() : List<MarketItem>{
    return listOf(
        MarketItem(
            name = "Norwich Market",
            busyness = 0.8f
        ),
        MarketItem(
            name = "Worstead Estate Farmers Market",
            busyness = 0.2f
        ),
        MarketItem(
            name = "Sheringham Market",
            busyness = 0.1f
        )
    )
}