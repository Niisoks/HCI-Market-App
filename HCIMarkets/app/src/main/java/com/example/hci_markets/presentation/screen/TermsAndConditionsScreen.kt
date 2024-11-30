package com.example.hci_markets.presentation.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.hci_markets.R
import com.example.hci_markets.presentation.ui.common.AcceptableScrollingText
import com.example.hci_markets.presentation.ui.theme.HCIMarketsTheme

@Composable
fun TermsAndConditionsScreen(
    onAccept: () -> Unit = {},
    onDecline: () -> Unit = {}
){
    Column {
        Surface(Modifier.padding(horizontal = dimensionResource(R.dimen.padding_medium))){
            Text(
                stringResource(id = R.string.terms_disclaimer),
                Modifier.padding(vertical = dimensionResource(id = R.dimen.padding_medium)),
                textAlign = TextAlign.Center
            )
        }
        Divider(Modifier.padding(top = dimensionResource(id = R.dimen.padding_small)))
        AcceptableScrollingText(
            stringResource(id = R.string.terms_title),
            stringResource(id = R.string.lorem_terms),
            onAccept,
            onDecline
        )
    }
}

@Preview
@Composable
private fun Preview(){
    HCIMarketsTheme {
        Surface {
            TermsAndConditionsScreen()
        }
    }
}