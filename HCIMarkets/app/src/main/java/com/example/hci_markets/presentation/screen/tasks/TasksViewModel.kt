package com.example.hci_markets.presentation.screen.tasks

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class TasksViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(TasksUIState())
    val uiState: StateFlow<TasksUIState> = _uiState.asStateFlow()

    fun updateMarketSelectionComplete(isComplete: Boolean) {
        _uiState.value = _uiState.value.copy(marketSelectionComplete = isComplete)
        updateCompleteTasks()
    }

    fun updateLocationPermissionsComplete(isComplete: Boolean) {
        _uiState.value = _uiState.value.copy(locationPermissionsComplete = isComplete)
        updateCompleteTasks()
    }

    fun updateHomeLocationComplete(isComplete: Boolean) {
        _uiState.value = _uiState.value.copy(homeLocationComplete = isComplete)
        updateCompleteTasks()
    }

    fun updateCompleteTasks(){
        _uiState.value = _uiState.value.copy(tasksComplete = listOf(
            _uiState.value.marketSelectionComplete,
            _uiState.value.locationPermissionsComplete,
            _uiState.value.homeLocationComplete
        ).count { it })
    }

    fun updateTotalTasks(number: Int){
        _uiState.value = _uiState.value.copy(totalTasks = number)
        updateCompleteTasks()
    }

    fun stateOverride(
        marketSelectionComplete: Boolean = false,
        locationPermissionsComplete: Boolean = false,
        homeLocationComplete: Boolean = false,
        tasksComplete: Int = 0,
        totalTasks: Int = 3
    ){
        _uiState.value = _uiState.value.copy(
            marketSelectionComplete = marketSelectionComplete,
            locationPermissionsComplete = locationPermissionsComplete,
            homeLocationComplete = homeLocationComplete,
            tasksComplete = tasksComplete,
            totalTasks = totalTasks
        )
        updateCompleteTasks()
    }
}
