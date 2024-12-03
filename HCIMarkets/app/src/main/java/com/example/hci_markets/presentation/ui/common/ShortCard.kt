package com.example.hci_markets.presentation.ui.common


import android.content.res.Configuration
import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hci_markets.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShortCard(
    title: String,
    description: String,
    enabled: Boolean = true,
    onClick: () -> Unit = {},
    rightContent: @Composable() (() -> Unit)
){
    ElevatedCard(
        onClick = onClick,
        enabled = enabled,
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = description,
                    style = MaterialTheme.typography.bodyMedium,
                )
            }
            rightContent.invoke()
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun Preview(){
    ShortCard(
        "Select a Market",
        "Select a market so we know what information to show you.",
    ) {
        Box {
            Image(
                painter = painterResource(id =  R.drawable.outline_add_business_24),
                contentDescription = null,
                modifier = Modifier
                    .size(64.dp)
                    .padding(start = 16.dp),
            )
        }
    }
}

@Preview
@Composable
private fun PreviewDisabled(){
    ShortCard(
        "Select a Market",
        "Select a market so we know what information to show you.",
        enabled = false
    ) {
        Box {
            Image(
                painter = painterResource(id =  R.drawable.outline_add_business_24),
                contentDescription = null,
                modifier = Modifier
                    .size(64.dp)
                    .padding(start = 16.dp),
            )
        }
    }
}