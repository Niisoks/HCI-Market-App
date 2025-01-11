package com.example.hci_markets.presentation.screen.selectMarketScreen

import FloatingButton
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hci_markets.R
import com.example.hci_markets.domain.model.MarketItem
import com.example.hci_markets.presentation.ui.common.FloatingDialog
import com.example.hci_markets.presentation.ui.theme.HCIMarketsTheme
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState
import java.util.UUID

@Composable
fun SelectMarketMap(
    markets: List<MarketItem> = listOf(),
    selectedMarkets: List<UUID> = listOf(),
    onMarketClick: (UUID) -> Unit = {},
){
    var dialogVisible = remember{mutableStateOf(true)}
    Surface {
        Box {
            val markerList = markets.map{ market ->
                rememberMarkerState(position = market.latLng)
            }
            val cameraPositionState = rememberCameraPositionState {
                runCatching { position = CameraPosition.fromLatLngZoom(markets[0].latLng, 15f) }
                    .onFailure { position = CameraPosition.fromLatLngZoom(LatLng(52.6286, 1.2929), 15f) }
            }
            GoogleMap(
                modifier = Modifier.fillMaxSize(),
                cameraPositionState = cameraPositionState,
                onMapClick = { latlng -> }
            ) {
                if(markerList.isNotEmpty()){
                    for(i in 0..markerList.lastIndex) {
                        Marker(
                            state = markerList[i],
                            icon = if(selectedMarkets.contains(markets[i].uid))
                                BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)
                            else BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED),
                            onClick = { marker ->
                                onMarketClick(markets[i].uid)
                                false
                            }
                        )
                    }
                }

            }

            FloatingDialog(
                stringResource(R.string.set_market_dialog),
                dialogVisible.value,
                Modifier.padding(12.dp),
                dialogOnClose = {dialogVisible.value = false}
            )
        }
    }
}

@Preview
@Composable
private fun Preview(){
    HCIMarketsTheme {
        SelectMarketMap(
        )
    }
}