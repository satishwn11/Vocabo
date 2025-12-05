package com.devsatish.vocabo.Screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.devsatish.vocabo.Model.BottomNavItem
import com.devsatish.vocabo.R
import com.devsatish.vocabo.ui.theme.DisplayGreen
import com.devsatish.vocabo.ui.theme.myGreen
import com.devsatish.vocabo.ui.theme.softgray
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {

    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    BackHandler(enabled = drawerState.isOpen) {
        scope.launch { drawerState.close() }
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    // Top small accent line
                    Divider(
                        modifier = Modifier.width(70.dp),
                        thickness = 3.dp,
                        color = DisplayGreen
                    )

                    Spacer(Modifier.height(12.dp))

                    // Main Card Box
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(160.dp)
                            .shadow(8.dp, shape = RoundedCornerShape(16.dp))
                            .background(Color.White, shape = RoundedCornerShape(16.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Vocabo",
                            fontSize = 40.sp,
                            color = DisplayGreen,
                            fontWeight = FontWeight.ExtraBold
                        )
                    }

                    Spacer(Modifier.height(16.dp))

                    Divider(thickness = 1.dp, color = Color.Gray.copy(alpha = 0.3f))

                    Spacer(Modifier.height(12.dp))

                    // Button
                    Button(
                        onClick = { navController.navigate("TimerScreen") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(55.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFFFFFFF),
                            contentColor = Color.Black
                        ),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text(
                            "Usage Statistics",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }


            }
        }
    ) {

        val navItemList = listOf(
            BottomNavItem("Home", R.drawable.home_icon),
            BottomNavItem("Reading", R.drawable.reading)
        )

        var selectedIndex by remember {
            mutableIntStateOf(0)
        }

        Scaffold(
            topBar = {
                if (selectedIndex == 0) {
                    TopAppBar(
                        title = {
                            Text(
                                "Vocabo",
                                fontSize = 30.sp,
                                fontWeight = FontWeight.SemiBold,
                                fontFamily = FontFamily.SansSerif
                            )
                        },
                        navigationIcon = {
                            IconButton(onClick = {
                                scope.launch {
                                    drawerState.open()
                                }
                            }) {
                                Icon(
                                    painter = painterResource(R.drawable.menu_icon),
                                    contentDescription = "menu icon",
                                    modifier = Modifier.size(36.dp)
                                )
                            }
                        },
                        colors = TopAppBarDefaults.topAppBarColors(
                            containerColor = softgray, titleContentColor = myGreen
                        ),
                        actions = {
                            Icon(
                                painter = painterResource(R.drawable.write_icon),
                                contentDescription = "note icon",
                                modifier = Modifier
                                    .size(40.dp)
                                    .clickable {
                                        navController.navigate("NoteScreen")
                                    })
                        })
                }
            },
            bottomBar = {
                NavigationBar {
                    navItemList.forEachIndexed { index, item ->
                        NavigationBarItem(
                            selected = selectedIndex == index,
                            onClick = {
                                selectedIndex = index
                            },
                            icon = {
                                Icon(
                                    painter = painterResource(item.icon),
                                    contentDescription = null
                                )
                            },
                            label = { Text(text = item.label) }
                        )
                    }
                }
            }


        ) { paddingValues ->

            when (selectedIndex) {
                0 -> {
                    HomeContent(
                        navController = navController,
                        paddingValues = paddingValues
                    )
                }

                1 -> {
                    ReadingScreen(
                        apiKey = "31ecf46881364f0ab466b3bf202d9f88",
                        navController = navController
                    )
                }
            }

        }
    }
}