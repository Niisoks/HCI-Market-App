package com.example.hci_markets

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.hci_markets.domain.model.MarketItem
import com.example.hci_markets.domain.model.createMarketItems
import com.example.hci_markets.util.PrefKeys
import com.example.hci_markets.util.areLocationPermissionsGranted
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.Priority
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.util.UUID

class MainViewModel : ViewModel() {
    private val _appState = MutableStateFlow(AppStateModel())
    val appState: StateFlow<AppStateModel> = _appState.asStateFlow()
    var markets: List<MarketItem> = createMarketItems()

    @SuppressLint("MissingPermission")
    fun beginListeningToLocation(context: Context, locationProvider: FusedLocationProviderClient){
        if(areLocationPermissionsGranted(context)) {
            locationProvider.getCurrentLocation(Priority.PRIORITY_BALANCED_POWER_ACCURACY, null).addOnSuccessListener { location ->
                _appState.update{
                    _appState.value.copy( location =
                        LatLng(location.latitude, location.longitude)
                    )
                }
            }.addOnFailureListener{
                Log.e("Main", "Dont work", )
            }
        }
    }

    fun setHomeLocation(location: LatLng){
        _appState.update{
            _appState.value.copy( homeLocation =
                LatLng(location.latitude, location.longitude)
            )
        }
    }

    fun closeDialog() {
        _appState.update {
            _appState.value.copy( dialogVisible =
                false
            )
        }
    }

    fun openDialog() {
        _appState.update {
            _appState.value.copy( dialogVisible =
                true
            )
        }
    }

    fun load(prefs: SharedPreferences){
        markets = createMarketItems().filter {
            (it.location == "Norwich" && prefs.getBoolean(PrefKeys.NORWICH_SELECTED, false)) ||
            (it.location == "Sheringham" &&  prefs.getBoolean(PrefKeys.SHERINGHAM_SELECTED, false)) ||
            (it.location == "Worstead" &&  prefs.getBoolean(PrefKeys.WORSTEAD_SELECTED, false))
        }
    }
}