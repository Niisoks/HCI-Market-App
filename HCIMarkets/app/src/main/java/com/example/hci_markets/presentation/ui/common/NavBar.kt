package com.example.hci_markets.presentation.ui.common

import FloatingButton
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hci_markets.R
import com.example.hci_markets.presentation.ui.theme.HCIMarketsTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavBar(
    title: String = "",
    currentLocation: NavigationLocations,
    showBack: Boolean = false,
    onBackPress: () -> Unit = {},
    navHome: () -> Unit = {},
    navMap: () -> Unit = {},
    navMarkets: () -> Unit = {},
    navNews: () -> Unit = {},
    navSettings: () -> Unit = {},
    content: @Composable () -> Unit = {}
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Column (Modifier.width(200.dp)){
                    IconButton(onClick = {
                        scope.launch {
                            drawerState.close()
                        }
                    }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                    Text(
                        stringResource(R.string.home),
                        Modifier
                            .background(if(currentLocation == NavigationLocations.HOME)Color.LightGray else Color.Transparent)
                            .padding(12.dp)
                            .fillMaxWidth()
                            .clickable{
                                navHome.invoke()
                                scope.launch { drawerState.close() }
                            }
                    )
                    Text(
                        stringResource(R.string.map),
                        Modifier
                            .background(if(currentLocation == NavigationLocations.MAP)Color.LightGray else Color.Transparent)
                            .padding(12.dp)
                            .fillMaxWidth()
                            .clickable{
                                navMap.invoke()
                                scope.launch { drawerState.close() }
                            }

                    )
                    Text(
                        stringResource(R.string.markets),
                        Modifier.background(if(currentLocation == NavigationLocations.MARKETS)Color.LightGray else Color.Transparent)
                            .padding(12.dp)
                            .fillMaxWidth()
                            .clickable{
                                navMarkets.invoke()
                                scope.launch { drawerState.close() }
                            }
                    )
                    Text(
                        stringResource(R.string.news),
                        Modifier.background(if(currentLocation == NavigationLocations.NEWS)Color.LightGray else Color.Transparent)
                            .padding(12.dp)
                            .fillMaxWidth()
                            .clickable{
                                navNews.invoke()
                                scope.launch { drawerState.close() }
                            }
                    )
                    Text(
                        stringResource(R.string.settings),
                        Modifier.background(if(currentLocation == NavigationLocations.SETTINGS)Color.LightGray else Color.Transparent)
                            .padding(12.dp)
                            .fillMaxWidth()
                            .clickable{
                                navSettings.invoke()
                                scope.launch { drawerState.close() }
                            }
                    )
                }
            }
        }
    ) {
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        titleContentColor = MaterialTheme.colorScheme.primary,
                    ),
                    title = {
                        Text(title)
                    },
                    navigationIcon = {
                        IconButton(onClick = {
                            scope.launch {
                                drawerState.open()
                            }
                        }) {
                            Icon(
                                imageVector = Icons.Default.Menu,
                                contentDescription = "Menu"
                            )
                        }
                    },
                )
            },
        ) { innerPadding ->
            Box(Modifier.padding(innerPadding).fillMaxSize()) {
                content.invoke()
                if(showBack) {
                    FloatingButton(
                        R.drawable.baseline_arrow_back_ios_24,
                        onClick = onBackPress,
                        modifier = Modifier.align(Alignment.BottomEnd)
                    )
                }
            }
        }
    }
}


@Preview
@Composable
private fun Preview(){
    HCIMarketsTheme {
        NavBar(currentLocation = NavigationLocations.MAP)
    }
}