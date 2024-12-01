package com.example.hci_markets

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import android.Manifest
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.hci_markets.presentation.nav.Screen
import com.example.hci_markets.presentation.screen.TasksScreen.TasksScreen
import com.example.hci_markets.presentation.screen.TasksScreen.TasksViewModel
import com.example.hci_markets.presentation.screen.TermsAndConditionsScreen
import com.example.hci_markets.presentation.ui.theme.HCIMarketsTheme
import com.example.hci_markets.util.PrefKeys
import com.example.hci_markets.util.areLocationPermissionsGranted

class MainActivity : ComponentActivity() {
    private lateinit var requestPermissionLauncher: ActivityResultLauncher<Array<String>>
    private lateinit var tasksViewModel: TasksViewModel

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

        val prefs = getSharedPreferences(PrefKeys.APP_PREFERENCES, MODE_PRIVATE)
        val termsAccepted = prefs.getBoolean(PrefKeys.TERMS_ACCEPTED, false)

        if (termsAccepted) {
            Log.i("Main", "Terms accepted already")
        }

        val tasksComplete = listOf(
            areLocationPermissionsGranted(this@MainActivity),
            prefs.getBoolean(PrefKeys.HOME_SET, false),
            prefs.getBoolean(PrefKeys.MARKET_SET, false)
        ).all { it }

        setContent {
            val navController = rememberNavController()
            HCIMarketsTheme {
                NavHost(
                    navController = navController,
                    startDestination = if (!termsAccepted) Screen.Terms.route
                    else if (!tasksComplete) Screen.Tasks.route
                    else Screen.Tasks.route
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
                            onHomeClick = {},
                            onContinueClick = {}
                        )
                    }
                }
            }
        }
    }
}