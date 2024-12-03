package com.example.hci_markets.domain.model

import androidx.annotation.DrawableRes

data class NewsItem(
    val title: String,
    val location: String,
    @DrawableRes val image: Int, // This would be done with coil (or alternative) in a non hifi prototype btw
    val description: String,
    val url: String,
)
