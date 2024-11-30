package com.example.hci_markets.presentation.ui.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hci_markets.R
import com.example.hci_markets.presentation.ui.theme.HCIMarketsTheme

@Composable
fun AcceptableScrollingText(
    title: String,
    text: String,
    onAccept: () -> Unit = {},
    onDecline: () -> Unit = {}
) {
    val scrollState = rememberScrollState()
    ElevatedCard(
        modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_medium)),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(dimensionResource(id = R.dimen.padding_medium))
        ) {
            Column(
                Modifier
                    .weight(1f)
                    .verticalScroll(scrollState)
            ) {
                Text(
                    title,
                    modifier = Modifier.padding(
                        vertical = dimensionResource(id = R.dimen.padding_medium)
                    )
                )
                Text(text)
            }
            AcceptDeclineButtonRow(onAccept, onDecline)
        }
    }
}


@Preview
@Composable
private fun Preview(){
    HCIMarketsTheme {
        Surface {
            AcceptableScrollingText(
                "Terms and Conditions",
                stringResource(id = R.string.lorem_terms),
            )
        }
    }
}