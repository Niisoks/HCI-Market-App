package com.example.hci_markets.presentation.screen.selectMarketScreen

import androidx.lifecycle.ViewModel
import com.example.hci_markets.domain.model.MarketItem
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.util.UUID

class SelectMarketsViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(SelectMarketsUIState())
    val uiState: StateFlow<SelectMarketsUIState> = _uiState.asStateFlow()

    // I would never do it like this normally im just rushing
    fun populateMarkets(
        norwichSelected : Boolean = false,
        sheringhamSelected : Boolean = false,
        worsteadSelected : Boolean = false,
    ){
        val markets = listOf(
            MarketItem(
                name = "Norwich Market",
                latLng = LatLng(52.6286, 1.2929),
                busyness = 0.8f
            ),
            MarketItem(
                name = "Sheringham Market",
                latLng = LatLng(52.9412, 1.2093),
                busyness = 0.8f
            ),
            MarketItem(
                name = "Worstead Estate Farmers Market",
                latLng = LatLng(52.7630, 1.4553),
                busyness = 0.8f
            ),
        )
        _uiState.update {
            _uiState.value.copy(
                markets = markets,
                visibleMarkets = markets.map { it.uid },
                selectedMarkets = listOfNotNull(
                    if (norwichSelected) markets[0].uid else null,
                    if (sheringhamSelected) markets[1].uid else null,
                    if (worsteadSelected) markets[2].uid else null
                )

            )
        }
    }

    fun selectMarket(uid: UUID) {
        if(_uiState.value.selectedMarkets.contains(uid)){
            _uiState.update {
                _uiState.value.copy(
                    selectedMarkets = _uiState.value.selectedMarkets.minus(uid)
                )
            }
        } else {
            _uiState.update {
                _uiState.value.copy(
                    selectedMarkets = _uiState.value.selectedMarkets.plus(uid)
                )
            }
        }
    }
}
