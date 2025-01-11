package com.example.hci_markets.presentation.screen.selectHomeScreen

import FloatingButton
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hci_markets.R
import com.example.hci_markets.presentation.ui.common.FloatingDialog
import com.example.hci_markets.presentation.ui.theme.HCIMarketsTheme
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState

@Composable
fun SelectHomeScreen(
    currentLocation: LatLng,
    selectedLocation: LatLng,
    selectLocation: (LatLng) -> Unit = {},
    selectCurrentLocation: () -> Unit = {},
    onSave: () -> Unit = {},
    dialogOnClose: () -> Unit = {},
    dialogVisible: Boolean
){
    Surface {
        Box {
            val markerState = rememberMarkerState(position = selectedLocation)
            val cameraPositionState = rememberCameraPositionState {
                position = CameraPosition.fromLatLngZoom(currentLocation, 100f)
            }
            LaunchedEffect(selectedLocation) {
                markerState.position = selectedLocation
                cameraPositionState.position =
                    CameraPosition.fromLatLngZoom(selectedLocation, 15f)
            }
            GoogleMap(
                modifier = Modifier.fillMaxSize(),
                cameraPositionState = cameraPositionState,
                onMapClick = { latlng ->
                    selectLocation(latlng)
                }
            ) {
                Marker(
                    state = markerState
                )
            }

            Column(modifier = Modifier.align(Alignment.BottomEnd)
                .padding(12.dp)){
                FloatingButton(
                    R.drawable.outline_my_location_24,
                    onClick = {
                        selectCurrentLocation.invoke()
                    }
                )
                FloatingButton(
                    R.drawable.outline_save_24,
                    onClick = {
                        onSave.invoke()
                    }
                )
            }
            FloatingDialog(
                stringResource(R.string.set_home_dialog),
                dialogVisible,
                Modifier.padding(12.dp),
                dialogOnClose = dialogOnClose
            )
        }
    }
}

@Preview
@Composable
private fun Preview(){
    HCIMarketsTheme {
        SelectHomeScreen(
            selectedLocation = LatLng(1.0, 1.0), currentLocation = LatLng(1.0, 1.0),
            selectLocation = {},
            selectCurrentLocation = {},
            onSave = {},
            dialogOnClose = {},
            dialogVisible = true
        )
    }
}