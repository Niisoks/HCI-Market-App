package com.example.hci_markets.presentation.screen.homeScreen

import android.content.res.Configuration
import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hci_markets.R
import com.example.hci_markets.presentation.ui.common.ShortCard

@Composable
fun BusyCard(
    title: String,
    busyness: Float,
    enabled: Boolean = true,
    onClick: () -> Unit = {}
){
    val busynessDrawableRes = remember{ busynessToGraph(busyness) }
    val busynessStringRes = remember{ busynessToString(busynessDrawableRes) }
    ShortCard(
        title = title,
        description = stringResource(id = busynessStringRes),
        enabled = enabled,
        onClick = onClick
    ) {
        Box {
            Image(
                painter = painterResource(id = busynessDrawableRes),
                contentDescription = null,
                modifier = Modifier
                    .size(64.dp),
                alpha = if (enabled) 1f else 0.5f
            )
        }
    }
}

@DrawableRes
fun busynessToGraph(busyness: Float): Int {
    return when {
        busyness <= 0.25f -> R.drawable.quiet
        busyness <= 0.5f -> R.drawable.notbusy
        busyness <= 0.75f -> R.drawable.busy
        else -> R.drawable.verybusy
    }
}

@StringRes
fun busynessToString(@DrawableRes res: Int) : Int{
    return when(res) {
         R.drawable.quiet -> R.string.quiet
        R.drawable.notbusy -> R.string.not_busy
        R.drawable.busy -> R.string.busy
        else -> R.string.very_busy
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun PreviewQuiet(){
    BusyCard(
        "Norwich Market",
        busyness = 0.1f
    )
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun PreviewNotBusy(){
    BusyCard(
        "Norwich Market",
        busyness = 0.3f
    )
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun PreviewBusy(){
    BusyCard(
        "Norwich Market",
        busyness = 0.6f
    )
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun PreviewVeryBusy(){
    BusyCard(
        "Norwich Market",
        busyness = 1f
    )
}