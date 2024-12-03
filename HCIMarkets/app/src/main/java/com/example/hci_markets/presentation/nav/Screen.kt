package com.example.hci_markets.presentation.nav

sealed class Screen(val route: String) {
    data object Terms : Screen(route = "terms")
    data object Tasks : Screen(route = "tasks")
    data object Home : Screen(route = "home")
}