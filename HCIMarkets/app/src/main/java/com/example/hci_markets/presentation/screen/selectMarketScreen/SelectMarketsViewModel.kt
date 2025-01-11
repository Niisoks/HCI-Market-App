package com.example.hci_markets.presentation.screen.selectMarketScreen

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import com.example.hci_markets.domain.model.MarketItem
import com.example.hci_markets.presentation.screen.marketsScreen.PeakTimes
import com.example.hci_markets.util.PrefKeys
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
                busyness = 0.8f,
                location = "Norwich",
                openingTimes = "9am - 4pm",
                openingDays = "Mon - Sun",
                peak = PeakTimes.TWELVE,
                latLng = LatLng(52.630886, 1.297355)
            ),
            MarketItem(
                name = "Sheringham Market",
                busyness = 0.5f,
                location = "Sheringham",
                openingTimes = "11am - 4pm",
                openingDays = "Wed - Sun",
                peak = PeakTimes.THREE,
                latLng = LatLng(52.9412, 1.2093)
            ),
            MarketItem(
                name = "Worstead Estate Farmers Market",
                busyness = 0.2f,
                location = "Worstead",
                openingTimes = "8am - 1pm",
                openingDays = "Sat - Sun",
                peak = PeakTimes.NINE,
                latLng = LatLng(52.7630, 1.4553)
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

    fun save(prefs: SharedPreferences){
        val selectedMarkets = _uiState.value.selectedMarkets
        val markets = _uiState.value.markets
        prefs.edit()
            .putBoolean(PrefKeys.NORWICH_SELECTED, selectedMarkets.contains(markets[0].uid))
            .putBoolean(PrefKeys.SHERINGHAM_SELECTED, selectedMarkets.contains(markets[1].uid))
            .putBoolean(PrefKeys.WORSTEAD_SELECTED, selectedMarkets.contains(markets[2].uid))
            .apply()
    }

    fun updateSearchText(string: String){
        val visible = if(string.isBlank()) _uiState.value.markets.map { it.uid } else _uiState.value.markets.filter { it.name.contains(string) }.map { it.uid }
        _uiState.update {
            _uiState.value.copy(
                text = string,
                visibleMarkets = visible
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
