package com.example.hci_markets.domain.model

import com.google.android.gms.maps.model.LatLng
import java.util.UUID

data class MarketItem (
    val uid: UUID = UUID.randomUUID(),
    val name: String,
    val latLng: LatLng,
    val busyness: Float
)