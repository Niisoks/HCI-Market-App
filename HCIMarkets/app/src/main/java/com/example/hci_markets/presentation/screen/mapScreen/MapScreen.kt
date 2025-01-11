package com.example.hci_markets.presentation.screen.mapScreen

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hci_markets.R
import com.example.hci_markets.presentation.ui.common.FloatingDialog
import com.example.hci_markets.presentation.ui.theme.HCIMarketsTheme

@Composable
fun MapScreen(){
    val context = LocalContext.current
    val visible = remember{ mutableStateOf(true) }
    Box() {
        Image(
            painter = painterResource(id = R.drawable.linemap),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )
        FloatingDialog(
            text = stringResource(R.string.directions_to_market),
            visible = visible.value,
            buttonTitle = stringResource(R.string.open_in_maps),
            buttonAction = {
                val latitude = 52.6286
                val longitude = 1.2929
                val gmmIntentUri = Uri.parse("google.navigation:q=$latitude,$longitude")
                val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri).apply {
                    setPackage("com.google.android.apps.maps")
                }
                try {
                    context.startActivity(mapIntent)
                } catch (e: Exception) {
                    // Fallback if Google Maps is not installed
                    Toast.makeText(context, "Google Maps not installed", Toast.LENGTH_SHORT).show()
                }
            },
            modifier = Modifier.padding(12.dp)
        ) { }
    }
}

@Preview
@Composable
private fun Preview(){
    HCIMarketsTheme {
        MapScreen()
    }
}