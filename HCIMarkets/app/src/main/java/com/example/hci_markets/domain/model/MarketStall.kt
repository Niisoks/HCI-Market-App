package com.example.hci_markets.domain.model

import android.media.Image
import androidx.annotation.DrawableRes
import com.example.hci_markets.presentation.screen.marketsScreen.PeakTimes
import com.google.android.gms.maps.model.LatLng
import java.util.UUID

data class MarketStall(
    val uid: UUID = UUID.randomUUID(),
    val name: String,
    val description: String,
    val latLng: LatLng = LatLng(52.630886, 1.297355),
    val busyness: Float,
    @DrawableRes val image: Int, // This would be done with coil (or alternative) in a non hifi prototype btw
    @DrawableRes val profileImage: Int,
    val acceptsCash: Boolean = true,
    val acceptsCard: Boolean = true,
    val vegetarian: Boolean = true,
    val vegan: Boolean = true,
    val stallNo: Int = 0,
    val rowLetter: String = "A",
    val openingToClosingTime: String = "12pm - 5pm",
    val openDays: String = "Mon - Sat",
    val busiest: PeakTimes = PeakTimes.TWELVE,
    val phone: String = "+44 789789789",
    val email: String = "testoffakeemail@norwichmarket.uk",
    val menu: List<RestaurantMenu> = listOf()
)

enum class BusiestAt(){
    BREAKFAST,
    LUNCH,
    DINNER,
    EVENING,
    MORNING,
    NIGHT
}