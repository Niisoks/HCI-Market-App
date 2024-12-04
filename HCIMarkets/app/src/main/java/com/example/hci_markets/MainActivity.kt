package com.example.hci_markets

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import android.Manifest
import android.annotation.SuppressLint
import android.content.SharedPreferences
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.hci_markets.domain.model.MarketItem
import com.example.hci_markets.domain.model.NewsItem
import com.example.hci_markets.presentation.nav.Screen
import com.example.hci_markets.presentation.screen.tasks.TasksScreen
import com.example.hci_markets.presentation.screen.TermsAndConditionsScreen
import com.example.hci_markets.presentation.screen.homeScreen.HomeScreen
import com.example.hci_markets.presentation.screen.selectHomeScreen.SelectHomeScreen
import com.example.hci_markets.presentation.screen.tasks.TasksScreen
import com.example.hci_markets.presentation.screen.tasks.TasksViewModel
import com.example.hci_markets.presentation.ui.theme.HCIMarketsTheme
import com.example.hci_markets.util.PrefKeys
import com.example.hci_markets.util.areLocationPermissionsGranted
import com.example.hci_markets.util.putDouble
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.LatLng

class MainActivity : ComponentActivity() {
    private lateinit var requestPermissionLauncher: ActivityResultLauncher<Array<String>>
    private lateinit var tasksViewModel: TasksViewModel
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private val mainViewModel: MainViewModel by viewModels<MainViewModel>()


    @SuppressLint("MissingPermission")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestPermissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { permissions ->
            val fineLocationGranted = permissions[Manifest.permission.ACCESS_FINE_LOCATION] ?: false
            val coarseLocationGranted = permissions[Manifest.permission.ACCESS_COARSE_LOCATION] ?: false

            val isGranted = fineLocationGranted || coarseLocationGranted
            tasksViewModel.updateLocationPermissionsComplete(isGranted)
            if (!isGranted) {
                Toast.makeText(
                    applicationContext,
                    "Please enable location permissions",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        val prefs = getSharedPreferences(PrefKeys.APP_PREFERENCES, MODE_PRIVATE)
        val termsAccepted = prefs.getBoolean(PrefKeys.TERMS_ACCEPTED, false)

        if (termsAccepted) {
            Log.i("Main", "Terms accepted already")
        }

        setContent {
            val navController = rememberNavController()
            HCIMarketsTheme {
                NavHost(
                    navController = navController,
                    startDestination = if (!termsAccepted) Screen.Terms.route
                    else if (!tasksComplete(this@MainActivity, prefs)) Screen.Tasks.route
                    else Screen.Home.route
                ) {
                    composable(route = Screen.Terms.route) {
                        TermsAndConditionsScreen(
                            onAccept = {
                                prefs.edit()
                                    .putBoolean(PrefKeys.TERMS_ACCEPTED, true)
                                    .apply()
                                navController.navigate(Screen.Tasks.route) {
                                    popUpTo(0) { inclusive = true }
                                    launchSingleTop = true
                                }
                            },
                            onDecline = {
                                Toast.makeText(
                                    this@MainActivity,
                                    R.string.terms_disclaimer,
                                    Toast.LENGTH_SHORT
                                ).show()
                                finish()
                            }
                        )
                    }
                    composable(route = Screen.Tasks.route) {
                        tasksViewModel = viewModel()
                        tasksViewModel.stateOverride(
                            marketSelectionComplete = false,
                            locationPermissionsComplete = areLocationPermissionsGranted(this@MainActivity),
                            homeLocationComplete = false,
                            totalTasks = 3
                        )

                        val tasksState = tasksViewModel.uiState.collectAsState()

                        TasksScreen(
                            marketSelectionComplete = tasksState.value.marketSelectionComplete,
                            locationPermissionsComplete = tasksState.value.locationPermissionsComplete,
                            homeLocationComplete = tasksState.value.homeLocationComplete,
                            tasksComplete = tasksState.value.tasksComplete,
                            totalTasks = tasksState.value.totalTasks,
                            onMarketClick = {},
                            onLocationClick = {
                                if(!areLocationPermissionsGranted(this@MainActivity)) {
                                    requestPermissionLauncher.launch(
                                        arrayOf(
                                            Manifest.permission.ACCESS_FINE_LOCATION,
                                            Manifest.permission.ACCESS_COARSE_LOCATION,
                                        )
                                    )
                                }
                            },
                            onHomeClick = {
                                navController.navigate(Screen.SelectHome.route)
                            },
                            onContinueClick = {
                                navController.navigate(Screen.Home.route) {
                                    if(tasksComplete(this@MainActivity, prefs)) {
                                        popUpTo(0) { inclusive = true }
                                        launchSingleTop = true
                                    }
                                }
                            }
                        )
                    }
                    composable(route = Screen.SelectHome.route){
                        val appState = mainViewModel.appState.collectAsStateWithLifecycle()
                        SelectHomeScreen(
                            appState.value.location,
                            appState.value.homeLocation ,
                            selectCurrentLocation = {
                                fusedLocationClient.lastLocation
                                    .addOnSuccessListener { location ->
                                        location?.let {
                                            mainViewModel.setHomeLocation(
                                                LatLng(
                                                    it.latitude,
                                                    it.longitude
                                                )
                                            )
                                        }
                                    }
                            },
                            selectLocation = {latlng ->
                                mainViewModel.setHomeLocation(latlng)
                            },
                            onSave = {
                                prefs.edit().putDouble(PrefKeys.HOME_LAT, appState.value.homeLocation.latitude)
                                    .putDouble(PrefKeys.HOME_LNG, appState.value.homeLocation.longitude)
                                    .apply()
                                Toast.makeText(this@MainActivity, R.string.successfully_saved, Toast.LENGTH_SHORT).show()
                                navController.popBackStack()
                            }
                        )
                    }
                    composable(route = Screen.Home.route) {

                        val recentNews = listOf(
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

                        val marketItems = listOf(
                            MarketItem(
                                name = "Norwich Market",
                                busyness = 0.8f
                            ),
                            MarketItem(
                                name = "Worstead Estate Farmers Market",
                                busyness = 0.6f
                            ),
                            MarketItem(
                                name = "Sheringham Market",
                                busyness = 0.3f
                            ),
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

                        HomeScreen(newsItems = recentNews, marketItems = marketItems)
                    }
                }
            }
        }
    }

}

private fun tasksComplete(activity: MainActivity, prefs: SharedPreferences) : Boolean{
    return listOf(
        areLocationPermissionsGranted(activity),
        prefs.getBoolean(PrefKeys.HOME_SET, false),
        prefs.getBoolean(PrefKeys.MARKET_SET, false)
    ).all { it }
}

