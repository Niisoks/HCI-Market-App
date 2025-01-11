package com.example.hci_markets.presentation.screen.newsScreen

import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp


@Composable
fun TagChip(label: String, isSelected: Boolean, onSelectedChange: () -> Unit) {
    FilterChip(
        selected = isSelected,
        onClick = onSelectedChange,
        label = { Text(
            label,
            maxLines = 1,
            modifier = Modifier.width(80.dp),
            overflow = TextOverflow.Ellipsis
        ) },
        modifier = Modifier.height(32.dp),
        leadingIcon = if (isSelected) {
            {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = "Selected",
                    modifier = Modifier.size(18.dp)
                )
            }
        } else null
    )
}