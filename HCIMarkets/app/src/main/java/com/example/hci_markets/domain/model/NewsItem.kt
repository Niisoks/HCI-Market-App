package com.example.hci_markets.domain.model

import androidx.annotation.DrawableRes
import com.example.hci_markets.R

data class NewsItem(
    val title: String,
    val location: String,
    @DrawableRes val image: Int, // This would be done with coil (or alternative) in a non hifi prototype btw
    val description: String,
    val url: String,
){
}

fun createNewsItems(): List<NewsItem>{
    return  listOf(
        NewsItem(
            title = "New pop-up shop now open",
            location = "Norwich Market",
            image = R.drawable.testnewsimage,
            description = "A new comic book pop-up has opened in Norwich Market. Paul Dunne founded it...",
            url = "https://example.com/news1"
        ),
        NewsItem(
            title = "New event this weekend",
            location = "Worstead Estate Farmers Market",
            image = R.drawable.testnewsimage,
            description = "Join us this weekend for a free community event in City Park...",
            url = "https://example.com/news2"
        )
    )
}
