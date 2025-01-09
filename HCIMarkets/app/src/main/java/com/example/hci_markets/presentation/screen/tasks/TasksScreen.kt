package com.example.hci_markets.presentation.screen.tasks

import FloatingButton
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hci_markets.R
import com.example.hci_markets.presentation.ui.common.ImageCard
import com.example.hci_markets.presentation.ui.common.TaskProgressIndicator
import com.example.hci_markets.presentation.ui.theme.HCIMarketsTheme

@Composable
fun TasksScreen(
    marketSelectionComplete: Boolean,
    locationPermissionsComplete: Boolean,
    homeLocationComplete: Boolean,
    tasksComplete: Int,
    totalTasks: Int,
    onMarketClick: () -> Unit = {},
    onLocationClick: () -> Unit = {},
    onHomeClick: () -> Unit = {},
    onContinueClick: () -> Unit = {}
){
    Box(Modifier.fillMaxSize()) {
        Surface(
            Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.surfaceVariant
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.padding(24.dp)
            ) {
                TaskProgressIndicator(
                    modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_medium)),
                    totalTasks = totalTasks,
                    completeTasks = tasksComplete
                )
                if (tasksComplete < totalTasks) {
                    ImageCard(
                        title = stringResource(id = R.string.enable_location_perm),
                        description = stringResource(id = R.string.enable_location_description),
                        image = R.drawable.outline_share_location_24,
                        enabled = !locationPermissionsComplete,
                        onClick = onLocationClick
                    )
                    ImageCard(
                        title = stringResource(id = R.string.set_home),
                        description = stringResource(id = R.string.set_home_description),
                        image = R.drawable.outline_add_home_24,
                        enabled = !homeLocationComplete,
                        onClick = onHomeClick
                    )
                    ImageCard(
                        title = stringResource(id = R.string.select_market),
                        description = stringResource(id = R.string.select_market_description),
                        image = R.drawable.outline_add_business_24,
                        enabled = !marketSelectionComplete,
                        onClick = onMarketClick
                    )
                } else {
                    Surface(
                        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant),
                        shape = RoundedCornerShape(12.dp),
                        color = Color.Unspecified
                    ) {
                        Column(
                            Modifier
                                .padding(16.dp)
                                .padding(vertical = 10.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Text(
                                stringResource(id = R.string.all_tasks_complete),
                                style = MaterialTheme.typography.headlineSmall,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                stringResource(id = R.string.all_tasks_complete_description),
                                style = MaterialTheme.typography.bodyLarge
                            )
                        }

                    }
                }
            }
        }
        FloatingButton(
            icon = R.drawable.baseline_arrow_forward_ios_24,
            text = "Continue",
            onClick = onContinueClick,
            modifier = Modifier.align(Alignment.BottomEnd)
                .padding(8.dp)
        )
    }
}

@Preview
@Composable
private fun Preview(){
    HCIMarketsTheme {
        TasksScreen(
            true,
            false,
            true,
            2,
            3
        )
    }
}

@Preview
@Composable
private fun CompletePreview(){
    HCIMarketsTheme {
        TasksScreen(
            true,
            true,
            true,
            3,
            3
        )
    }
}