package com.example.hci_markets.presentation.screen.marketsScreen.shopItem

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.example.hci_markets.R
import com.example.hci_markets.domain.model.RestaurantMenu
import com.example.hci_markets.presentation.screen.marketsScreen.PeakTimes
import com.example.hci_markets.presentation.ui.theme.HCIMarketsTheme
import kotlinx.coroutines.launch

@Composable
fun ShopItem(
    pfp: Int = R.drawable.tasteofshanghaipfp,
    image: Int = R.drawable.tasteofshanghai,
    name: String = "Taste of Shanghai",
    description: String = "Chinese Street Food",
    openingTimes: String = "12pm - 5pm",
    openingDays: String = "Mon - Sat",
    stall: String = "79",
    row: String = "D",
    busiestAt: PeakTimes = PeakTimes.TWELVE,
    cash: Boolean = true,
    card: Boolean = true,
    vegetarian: Boolean = true,
    vegan: Boolean = true,
    menu: List<RestaurantMenu> = listOf(),
    email: String = "test@fakemail.com",
    phone: String = "+44 789789789",
    website: String = "testsite.com"
) {
    val expanded = remember { mutableStateOf(true) }
    val menuVisible = remember { mutableStateOf(false) }
    val contactVisible = remember { mutableStateOf(false) }
    val context = LocalContext.current
    Box() {
        Card(
            modifier = Modifier.padding(8.dp).fillMaxWidth(),
            elevation = CardDefaults.cardElevation(4.dp),
            shape = RoundedCornerShape(8.dp)
        ) {
            Column(
                modifier = Modifier.padding(8.dp),
            ) {
                Row(
                    modifier = Modifier.padding(4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(pfp),
                        contentDescription = null,
                        modifier = Modifier
                            .height(36.dp)
                            .clip(CircleShape),
                        contentScale = ContentScale.Fit
                    )
                    Column(Modifier.padding(start = 12.dp)) {
                        Text(name, style = MaterialTheme.typography.titleSmall,)
                        Text(description, style = MaterialTheme.typography.labelSmall,)
                    }
                }
                if(expanded.value) {
                    Image(
                        painter = painterResource(image),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(150.dp)
                            .clip(RoundedCornerShape(4.dp)),
                        contentScale = ContentScale.Crop
                    )
                    Row(Modifier.fillMaxWidth().padding(vertical = 12.dp), horizontalArrangement = Arrangement.Center) {
                        TwoLineIconColumn(R.drawable.baseline_access_time_24, openingTimes, openingDays)
                        Spacer(Modifier.padding(horizontal = 64.dp))
                        TwoLineIconColumn(R.drawable.outline_my_location_24, "Stall $stall", "Row $row")
                    }
                    Row (Modifier.fillMaxWidth().padding(vertical = 12.dp), horizontalArrangement = Arrangement.Center) {
                        Column(Modifier.padding(12.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                            Text("Busiest at")
                            Text(when(busiestAt){
                                PeakTimes.NINE -> "Breakfast Time"
                                PeakTimes.TWELVE -> "Lunch Time"
                                PeakTimes.THREE -> "Dinner Time"
                            })
                        }

                        Image(
                            painter = painterResource(id = remember{
                                when(busiestAt){
                                    PeakTimes.NINE -> R.drawable._ambusy
                                    PeakTimes.TWELVE -> R.drawable._2pmbusy
                                    PeakTimes.THREE -> R.drawable._pmbusy
                                } }),
                            contentDescription = null,
                        )
                    }
                    Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.padding(8.dp)){
                        if(cash) IconLabelled(
                            R.drawable.baseline_attach_money_24,
                            "Accepts Cash",
                            modifier = Modifier.weight(1f)
                        )
                        if(card)IconLabelled(
                            R.drawable.baseline_credit_card_24,
                            "Accepts   Card",
                            modifier = Modifier.weight(1f)
                        )
                        if(vegan)IconLabelled(
                            R.drawable.baseline_grass_24,
                            "Vegan Options",
                            modifier = Modifier.weight(1f)
                        )
                        if(vegetarian) IconLabelled(
                            R.drawable.baseline_sunny_24,
                            "Vegetarian Options",
                            modifier = Modifier.weight(1f)
                        )
                    }
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween){
                        Button(onClick = {
                            contactVisible.value = true
                        }) {
                            Text("Contact")
                        }
                        Button(onClick = {
                            menuVisible.value = true
                        }) {
                            Text("Menu")
                        }
                        Button(onClick = {
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(website))
                            ContextCompat.startActivity(context, intent, null)
                        }) {
                            Text("Website")
                        }
                    }
                }
            }

        }
        IconButton(onClick = {
            expanded.value = !expanded.value
        },
            modifier = Modifier.align(Alignment.TopEnd).padding(12.dp)) {
            Icon(
                imageVector = Icons.Default.ArrowDropDown,
                contentDescription = "Expand"
            )
        }
        if(menuVisible.value){
            MenuDialog(menu) { menuVisible.value = false }
        }
        if(contactVisible.value){
            MenuDialog(remember{listOf(
                RestaurantMenu("Phone", phone),
                RestaurantMenu("Email", email)
            )}) { contactVisible.value = false }
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun Preview(){
    HCIMarketsTheme {
        ShopItem()
    }
}