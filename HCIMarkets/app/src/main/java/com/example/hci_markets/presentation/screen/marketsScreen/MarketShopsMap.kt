package com.example.hci_markets.presentation.screen.marketsScreen

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hci_markets.R
import com.example.hci_markets.presentation.ui.common.FloatingDialog

@Composable
fun MarketShopsMap() {
    val context = LocalContext.current
    val visible = remember{ mutableStateOf(true)}
    val showHeatmap = remember { mutableStateOf(false) }
    Box() {
        if(!showHeatmap.value) {
            Image(
                painter = painterResource(id = R.drawable.marketshops),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .clickable {
                        Toast.makeText(
                            context,
                            "Shop map feature not available in demo",
                            Toast.LENGTH_LONG
                        ).show()
                    },
                contentScale = ContentScale.FillBounds
            )
        } else {
            Image(
                painter = painterResource(id = R.drawable.marketshopsheatmap),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .clickable {
                        Toast.makeText(
                            context,
                            "Shop map feature not available in demo",
                            Toast.LENGTH_LONG
                        ).show()
                    },
                contentScale = ContentScale.FillBounds
            )
        }
        FloatingDialog(
            text = stringResource(R.string.select_shop),
            visible = visible.value,
            modifier = Modifier.padding(12.dp)
        ) { visible.value = !visible.value }
        HeatmapToggle(
            modifier = Modifier.align(Alignment.BottomCenter).padding(12.dp),
            showHeatmap.value,
            {showHeatmap.value = !showHeatmap.value})
    }
}

@Preview
@Composable
private fun Preview(){
    MarketShopsMap()
}