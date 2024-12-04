package com.example.hci_markets

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.hci_markets.util.areLocationPermissionsGranted
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.Priority
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MainViewModel : ViewModel() {
    private val _appState = MutableStateFlow(AppStateModel())
    val appState: StateFlow<AppStateModel> = _appState.asStateFlow()

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
}