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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.hci_markets.domain.model.MarketItem
import com.example.hci_markets.domain.model.NewsItem
import com.example.hci_markets.domain.model.createMarketItems
import com.example.hci_markets.domain.model.createNewsItems
import com.example.hci_markets.presentation.nav.Screen
import com.example.hci_markets.presentation.screen.tasks.TasksScreen
import com.example.hci_markets.presentation.screen.TermsAndConditionsScreen
import com.example.hci_markets.presentation.screen.homeScreen.HomeScreen
import com.example.hci_markets.presentation.screen.mapScreen.MapScreen
import com.example.hci_markets.presentation.screen.marketsScreen.MarketsScreen
import com.example.hci_markets.presentation.screen.newsScreen.NewsScreen
import com.example.hci_markets.presentation.screen.selectHomeScreen.SelectHomeScreen
import com.example.hci_markets.presentation.screen.selectMarketScreen.SelectMarketScreen
import com.example.hci_markets.presentation.screen.selectMarketScreen.SelectMarketsViewModel
import com.example.hci_markets.presentation.screen.settingsScreen.SettingsScreen
import com.example.hci_markets.presentation.screen.tasks.TasksScreen
import com.example.hci_markets.presentation.screen.tasks.TasksViewModel
import com.example.hci_markets.presentation.ui.common.NavBar
import com.example.hci_markets.presentation.ui.common.NavigationLocations
import com.example.hci_markets.presentation.ui.theme.HCIMarketsTheme
import com.example.hci_markets.util.PrefKeys
import com.example.hci_markets.util.areLocationPermissionsGranted
import com.example.hci_markets.util.getDouble
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

        if(prefs.contains(PrefKeys.HOME_LAT) && prefs.contains(PrefKeys.HOME_LNG) && prefs.getBoolean(PrefKeys.HOME_SET , false)) {
            mainViewModel.setHomeLocation(
                LatLng(
                    prefs.getDouble(PrefKeys.HOME_LAT, 0.0),
                    prefs.getDouble(PrefKeys.HOME_LNG, 0.0)
                )
            )
        }
        mainViewModel.load(prefs = prefs)

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
                            marketSelectionComplete = prefs.getBoolean(PrefKeys.MARKET_SET, false),
                            locationPermissionsComplete = areLocationPermissionsGranted(this@MainActivity),
                            homeLocationComplete = prefs.getBoolean(PrefKeys.HOME_SET, false),
                            totalTasks = 3
                        )

                        val tasksState = tasksViewModel.uiState.collectAsState()

                        TasksScreen(
                            marketSelectionComplete = tasksState.value.marketSelectionComplete,
                            locationPermissionsComplete = tasksState.value.locationPermissionsComplete,
                            homeLocationComplete = tasksState.value.homeLocationComplete,
                            tasksComplete = tasksState.value.tasksComplete,
                            totalTasks = tasksState.value.totalTasks,
                            onMarketClick = {
                                navController.navigate(Screen.SelectMarket.route)
                            },
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
                        LaunchedEffect(Unit){
                            mainViewModel.openDialog() // The whole dialog thing shouldnt be in here
                        }
                        val appState = mainViewModel.appState.collectAsStateWithLifecycle()
                        SelectHomeScreen(
                            appState.value.location,
                            appState.value.homeLocation,
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
                            selectLocation = { latlng ->
                                mainViewModel.setHomeLocation(latlng)
                            },
                            onSave = {
                                prefs.edit().putDouble(
                                    PrefKeys.HOME_LAT,
                                    appState.value.homeLocation.latitude
                                )
                                    .putDouble(
                                        PrefKeys.HOME_LNG,
                                        appState.value.homeLocation.longitude
                                    )
                                    .putBoolean(PrefKeys.HOME_SET, true)
                                    .apply()
                                Toast.makeText(
                                    this@MainActivity,
                                    R.string.successfully_saved,
                                    Toast.LENGTH_SHORT
                                ).show()
                                navController.popBackStack()
                            },
                            dialogOnClose = { mainViewModel.closeDialog() },
                            dialogVisible = appState.value.dialogVisible
                        )
                    }

                    composable(route = Screen.SelectMarket.route){
                        val viewModel : SelectMarketsViewModel = viewModel()
                        val uiState = viewModel.uiState.collectAsStateWithLifecycle()
                        LaunchedEffect(Unit) { viewModel.populateMarkets(
                            prefs.getBoolean(PrefKeys.NORWICH_SELECTED, false),
                            prefs.getBoolean(PrefKeys.SHERINGHAM_SELECTED, false),
                            prefs.getBoolean(PrefKeys.WORSTEAD_SELECTED, false),
                        ) }
                        SelectMarketScreen(
                            markets = uiState.value.markets,
                            selectedMarkets = uiState.value.selectedMarkets,
                            visibleMarkets = uiState.value.visibleMarkets,
                            onMarketClick = {viewModel.selectMarket(it)},
                            searchText = uiState.value.text,
                            onSearchTextChanged = {viewModel.updateSearchText(it)},
                            onSave = {
                                viewModel.save(prefs)
                                Toast.makeText(
                                    this@MainActivity,
                                    R.string.successfully_saved,
                                    Toast.LENGTH_SHORT
                                ).show()
                                prefs.edit().putBoolean(PrefKeys.MARKET_SET, true)
                                    .apply()
                                navController.popBackStack()
                            }
                        )
                    }

                    composable(route = Screen.Home.route) {
                        val recentNews = createNewsItems()
                        mainViewModel.load(prefs)

                        val marketItems = mainViewModel.markets

                        MasterNavBar(
                            navController = navController,
                            showBack = false
                        ){
                            HomeScreen(newsItems = recentNews, marketItems = marketItems)
                        }
                    }

                    composable(route = Screen.News.route) {
                        MasterNavBar(
                            currentLocation = NavigationLocations.NEWS,
                            navController = navController,
                            showBack = false
                        ) {
                            NewsScreen(
                                createNewsItems(),
                                createMarketItems()
                            )
                        }
                    }

                    composable(route = Screen.Map.route){
                        MasterNavBar(
                            currentLocation = NavigationLocations.MAP,
                            navController = navController,
                            showBack = true
                        ){
                            MapScreen()
                        }
                    }

                    composable(route = Screen.Markets.route){
                        MasterNavBar(
                            currentLocation = NavigationLocations.MARKETS,
                            navController = navController,
                            showBack = false
                        ){
                            MarketsScreen(
                                mainViewModel.markets,
                                { marketItem ->

                                }
                            )
                        }
                    }

                    composable(route = Screen.Settings.route){
                        MasterNavBar(
                            currentLocation = NavigationLocations.SETTINGS,
                            navController = navController,
                            showBack = true,
                            showSettings = false
                        ){
                            SettingsScreen(navController)
                        }
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

@Composable
fun MasterNavBar(
    currentLocation: NavigationLocations = NavigationLocations.HOME,
    navController: NavController,
    showBack: Boolean,
    showSettings: Boolean = true,
    content: @Composable () -> Unit = {}
){
    NavBar(
        stringResource(currentLocation.nameRes),
        currentLocation = currentLocation,
        showBack = showBack,
        onBackPress = {navController.popBackStack()},
        navHome = {navController.navigate(Screen.Home.route)},
        navMap = {navController.navigate(Screen.Map.route)},
        navMarkets = {navController.navigate(Screen.Markets.route)},
        navNews = {navController.navigate(Screen.News.route)},
        navSettings = {navController.navigate(Screen.Settings.route)},
        showSettings = showSettings
    ){
        content.invoke()
    }
}