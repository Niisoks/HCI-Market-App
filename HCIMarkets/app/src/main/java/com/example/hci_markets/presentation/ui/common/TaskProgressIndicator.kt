package com.example.hci_markets.presentation.ui.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hci_markets.R

@Composable
fun TaskProgressIndicator(
    modifier: Modifier = Modifier,
    totalTasks: Int,
    completeTasks: Int
){
    val percentageComplete = remember(completeTasks){ if (totalTasks > 0) (completeTasks.toFloat() / totalTasks) else 0f}
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.defaultMinSize(120.dp, 120.dp)
    ) {
        CircularProgressIndicator(
            progress = percentageComplete,
            modifier = Modifier.matchParentSize()
        )
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = stringResource(id = R.string.tasks_complete), textAlign = TextAlign.Center)
            Text(
                text = stringResource(id = R.string.x_of_y, completeTasks, totalTasks),
                style = MaterialTheme.typography.titleLarge
            )
        }
    }
}

@Preview
@Composable
private fun Preview(){
    TaskProgressIndicator(totalTasks = 10, completeTasks = 6)
}