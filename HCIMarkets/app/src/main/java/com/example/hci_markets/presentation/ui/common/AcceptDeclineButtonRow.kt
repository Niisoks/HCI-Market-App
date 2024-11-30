package com.example.hci_markets.presentation.ui.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.hci_markets.R

@Composable
fun AcceptDeclineButtonRow(
    onAccept: () -> Unit = {},
    onDecline: () -> Unit = {}
){
    Row(
        Modifier
            .fillMaxWidth()
            .padding(top = dimensionResource(id = R.dimen.padding_small)),
        horizontalArrangement = Arrangement.End
    ) {
        Button(onClick = onDecline) {
            Text(stringResource(R.string.decline))
        }
        Spacer(
            modifier = Modifier.padding(
                horizontal = dimensionResource(
                    id = R.dimen.padding_small)
            )
        )
        Button(onClick = onAccept) {
            Text(stringResource(R.string.accept))
        }
    }
}

@Preview
@Composable
private fun Preview(){
    AcceptDeclineButtonRow()
}