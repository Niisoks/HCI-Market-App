package com.example.hci_markets

import com.google.android.gms.maps.model.LatLng

data class AppStateModel(
    val location: LatLng = LatLng(52.62783, 1.29834),
    val homeLocation: LatLng = location
)
