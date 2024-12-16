package com.example.hci_markets.presentation.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import com.example.hci_markets.R

@Composable
fun TwoTabNavigation(
    tabOneName: String,
    tabTwoName: String,
    tabOneContent: @Composable () -> Unit = {},
    tabTwoContent: @Composable () -> Unit = {}

) {
    var selectedTab by remember{ mutableIntStateOf(0) }

    Column {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            TabNavigationItem(
                tabOneName,
                selectedTab == 0,
                Modifier.weight(1f),
                { selectedTab = 0 })
            TabNavigationItem(
                tabTwoName,
                selectedTab == 1,
                Modifier.weight(1f),
                { selectedTab = 1 })
        }
        if(selectedTab == 0){
            tabOneContent.invoke()
        } else {
            tabTwoContent.invoke()
        }
    }
}

@Composable
fun TabNavigationItem(
    title: String,
    selected: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
){
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .background(
                if (selected) MaterialTheme.colorScheme.primaryContainer
                else Color.Transparent
            )
            .clickable{onClick.invoke()}
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = title,
                color = MaterialTheme.colorScheme.primary,
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}



@Preview
@Composable
fun PreviewTabNavigation() {
    TwoTabNavigation(
        "Home",
        "Markets",
        { Surface(modifier = Modifier.fillMaxSize()){} },
        { Surface(modifier = Modifier.fillMaxSize(), color = Color.Red){} }
    )
}
