package com.example.hci_markets.presentation.nav

sealed class Screen(val route: String) {
    data object Terms : Screen(route = "terms")
    data object Tasks : Screen(route = "tasks")
    data object Home : Screen(route = "home")
    data object News : Screen(route = "news")
    data object Map : Screen(route = "map")
    data object SelectMarket : Screen(route = "select-market")
    data object SelectHome : Screen(route = "select-home")
}