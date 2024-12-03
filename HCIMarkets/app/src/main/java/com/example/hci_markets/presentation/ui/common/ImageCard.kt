package com.example.hci_markets.presentation.ui.common

import android.content.res.Configuration
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hci_markets.R

@Composable
fun ImageCard(
    title: String,
    description: String,
    @DrawableRes image:  Int,
    enabled: Boolean = true,
    onClick: () -> Unit = {}
){
    ShortCard(title = title, description = description, enabled = enabled, onClick = onClick) {
        Box {
            Image(
                painter = painterResource(id = image),
                contentDescription = null,
                modifier = Modifier
                    .size(64.dp)
                    .padding(start = 16.dp),
                alpha = if (enabled) 1f else 0.5f
            )
            if(!enabled){
                Image(painter = painterResource(id = R.drawable.baseline_verified_24), contentDescription = "")
            }
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun Preview(){
    ImageCard(
        "Select a Market",
        "Select a market so we know what information to show you.",
        image = R.drawable.outline_add_business_24
    )
}

@Preview
@Composable
private fun PreviewDisabled(){
    ImageCard(
        "Select a Market",
        "Select a market so we know what information to show you.",
        enabled = false,
        image = R.drawable.outline_add_business_24
    )
}