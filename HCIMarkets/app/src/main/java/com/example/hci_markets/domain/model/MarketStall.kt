package com.example.hci_markets.domain.model

import android.media.Image
import androidx.annotation.DrawableRes
import com.example.hci_markets.R
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
    val website: String = "https://fakewebsite.com/",
    val menu: List<RestaurantMenu> = listOf()
)

fun createMarkets(): List<MarketStall>{
    return listOf(
        MarketStall(
            name = "Taste of Shanghai",
            description = "Chinese Street Food",
            latLng = LatLng(52.62865071194084, 1.2925752711640752),
            busyness = 0.6f,
            image = R.drawable.tasteofshanghai,
            profileImage = R.drawable.tasteofshanghaipfp,
            acceptsCard = true,
            acceptsCash = true,
            vegan = true,
            vegetarian = true,
            stallNo = 79,
            rowLetter = "D",
            openingToClosingTime = "12pm - 5pm",
            openDays = "Mon - Sat",
            busiest = PeakTimes.TWELVE,
            phone = "+44 789789789",
            email = "testoffakeemail@norwichmarket.uk",
            website = "https://fakewebsite.com/",
            menu = listOf(
                RestaurantMenu("Chilli Chicken", "Crispy Chilli Chicken served with a choice of rice or noodles"),
                RestaurantMenu("Sweet and Sour Chicken", "Sweet and Sour Chicken served with a choice of rice or noodles"),
                RestaurantMenu("Chilli Beef", "Crispy Chilli Beef served with a choice of rice or noodles")
            )
        ),
        MarketStall(
            name = "Taste of Shanghai",
            description = "Chinese Street Food",
            latLng = LatLng(52.62865071194084, 1.2925752711640752),
            busyness = 0.6f,
            image = R.drawable.tasteofshanghai,
            profileImage = R.drawable.tasteofshanghaipfp,
            acceptsCard = true,
            acceptsCash = true,
            vegan = true,
            vegetarian = true,
            stallNo = 79,
            rowLetter = "D",
            openingToClosingTime = "12pm - 5pm",
            openDays = "Mon - Sat",
            busiest = PeakTimes.TWELVE,
            phone = "+44 789789789",
            email = "testoffakeemail@norwichmarket.uk",
            website = "https://fakewebsite.com/",
            menu = listOf(
                RestaurantMenu("Chilli Chicken", "Crispy Chilli Chicken served with a choice of rice or noodles"),
                RestaurantMenu("Sweet and Sour Chicken", "Sweet and Sour Chicken served with a choice of rice or noodles"),
                RestaurantMenu("Chilli Beef", "Crispy Chilli Beef served with a choice of rice or noodles")
            )
        ),
        MarketStall(
            name = "Taste of Shanghai",
            description = "Chinese Street Food",
            latLng = LatLng(52.62865071194084, 1.2925752711640752),
            busyness = 0.6f,
            image = R.drawable.tasteofshanghai,
            profileImage = R.drawable.tasteofshanghaipfp,
            acceptsCard = true,
            acceptsCash = true,
            vegan = true,
            vegetarian = true,
            stallNo = 79,
            rowLetter = "D",
            openingToClosingTime = "12pm - 5pm",
            openDays = "Mon - Sat",
            busiest = PeakTimes.TWELVE,
            phone = "+44 789789789",
            email = "testoffakeemail@norwichmarket.uk",
            website = "https://fakewebsite.com/",
            menu = listOf(
                RestaurantMenu("Chilli Chicken", "Crispy Chilli Chicken served with a choice of rice or noodles"),
                RestaurantMenu("Sweet and Sour Chicken", "Sweet and Sour Chicken served with a choice of rice or noodles"),
                RestaurantMenu("Chilli Beef", "Crispy Chilli Beef served with a choice of rice or noodles")
            )
        )
    )
}