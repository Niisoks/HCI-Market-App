package com.example.hci_markets.presentation.ui.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hci_markets.R
import com.example.hci_markets.presentation.ui.theme.HCIMarketsTheme

@Composable
fun FloatingDialog(
    text: String,
    visible: Boolean = true,
    modifier: Modifier = Modifier,
    buttonTitle: String = "",
    buttonAction: () -> Unit = {},
    dialogOnClose: () -> Unit,
){
    if(!visible) return
    ElevatedCard(modifier = modifier, elevation = CardDefaults.elevatedCardElevation(4.dp), onClick = dialogOnClose) {
        Box(Modifier.fillMaxWidth()){
            if(buttonTitle == "") {
                Icon(
                    modifier = Modifier.align(Alignment.TopEnd).size(48.dp).padding(12.dp),
                    painter = painterResource(R.drawable.outline_close_24),
                    contentDescription = null
                )
            }
            Row(
                modifier = Modifier.padding(24.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier.weight(1f),
                    text = text
                )
                if(buttonTitle != "") {
                    Button(
                        modifier = Modifier.weight(0.6f),
                        onClick = buttonAction
                    ) {
                        Text(
                            buttonTitle,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun Preview(){
    HCIMarketsTheme {
        FloatingDialog(
            "Directions to norwich market",
            dialogOnClose = {},
            buttonTitle = "Open in maps"
        )
    }
}