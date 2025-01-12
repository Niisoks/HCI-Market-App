package com.example.hci_markets.domain.model

import com.example.hci_markets.presentation.screen.marketsScreen.PeakTimes
import com.google.android.gms.maps.model.LatLng
import java.util.UUID

data class MarketItem (
    val uid: UUID = UUID.randomUUID(),
    val name: String,
    val location: String,
    val openingTimes: String,
    val openingDays: String,
    val peak: PeakTimes,
    val latLng: LatLng = LatLng(52.630886, 1.297355),
    val busyness: Float,
    val shops: List<MarketStall> = listOf()
)

fun createMarketItems() : List<MarketItem>{
    return listOf(
        MarketItem(
            name = "Norwich Market",
            busyness = 0.8f,
            location = "Norwich",
            openingTimes = "9am - 4pm",
            openingDays = "Mon - Sun",
            peak = PeakTimes.TWELVE,
            latLng = LatLng(52.630886, 1.297355),
            shops = createMarkets()
        ),
        MarketItem(
            name = "Worstead Estate Farmers Market",
            busyness = 0.2f,
            location = "Worstead",
            openingTimes = "8am - 1pm",
            openingDays = "Sat - Sun",
            peak = PeakTimes.NINE,
            latLng = LatLng(52.7630, 1.4553),
            shops = createMarkets()
        ),
        MarketItem(
            name = "Sheringham Market",
            busyness = 0.5f,
            location = "Sheringham",
            openingTimes = "11am - 4pm",
            openingDays = "Wed - Sun",
            peak = PeakTimes.THREE,
            latLng = LatLng(52.9412, 1.2093),
            shops = createMarkets()
        )
    )
}