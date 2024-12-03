package com.example.hci_markets.presentation.screen.tasks

data class TasksUIState(
    val marketSelectionComplete: Boolean = false,
    val locationPermissionsComplete: Boolean = false,
    val homeLocationComplete: Boolean = false,
    val tasksComplete: Int = 0,
    val totalTasks: Int = 3
)
