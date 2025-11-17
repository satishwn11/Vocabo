package com.devsatish.vocabo

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.devsatish.vocabo.Repository.BottomNavItem
import com.devsatish.vocabo.Screens.ReadingScreen
import com.devsatish.vocabo.ui.theme.myGreen
import com.devsatish.vocabo.ui.theme.softgray

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
               onDrawerClick : () -> Unit) {

        val navItemList = listOf(
            BottomNavItem("Home", R.drawable.home_icon),
            BottomNavItem("Reading", R.drawable.reading)
        )

    var selectedIndex by remember {
        mutableIntStateOf(0)
    }

    Scaffold( topBar = { TopAppBar(
                title = { Text(
                        "Vocabo",
                        fontSize = 30.sp,
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = FontFamily.SansSerif)
                },
                navigationIcon = {
                    IconButton(onClick = { onDrawerClick() }) {
                        Icon(
                            painter = painterResource(R.drawable.menu_icon),
                            contentDescription = "menu icon",
                            modifier = Modifier.size(36.dp)) } },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = softgray, titleContentColor = myGreen
                ),
                actions = { Icon(
                        painter = painterResource(R.drawable.write_icon),
                        contentDescription = "note icon",
                        modifier = Modifier.size(40.dp).clickable {
                                navController.navigate("NoteScreen")
                            }) }) },
        bottomBar = {
            NavigationBar {
                navItemList.forEachIndexed { index, item ->
                    NavigationBarItem(
                        selected = selectedIndex == index,
                        onClick = {
                            selectedIndex = index
                        },
                        icon = { Icon(painter = painterResource(item.icon),
                            contentDescription = null) },
                        label = { Text(text = item.label) }
                    )
                }
            }
        }



    ) { paddingValues ->

        when(selectedIndex) {
            0 -> {
                val myFont = FontFamily(
                    Font(R.font.impact),
                    Font(R.font.impacted),
                    Font(R.font.impacted),
                )

                Column(
                    modifier = Modifier.fillMaxSize()
                        .padding(paddingValues)
                        .padding(12.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Row(
                        modifier = Modifier.fillMaxWidth()
                            .clip(RoundedCornerShape(8.dp))
                            .background(color = Color(0xFFFFD99C))
                            .clickable(onClick = {
                                navController.navigate("LevelSelect")
                            })
                            .padding(15.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Spacer(Modifier.width(6.dp))

                        Box(
                            modifier = Modifier
                                .shadow(
                                    elevation = 8.dp,
                                    spotColor = Color.Black.copy(alpha = 1.5f)
                                )
                        ) {
                            Image(
                                painter = painterResource(R.drawable.image_wordmeaning),
                                contentDescription = "word meaning icon",
                                modifier = Modifier.size(100.dp)
                            )
                        }

                        Spacer(Modifier.width(16.dp))

                        Text("Word \nMeanings",
                            fontSize = 36.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = myFont,
                            style = TextStyle(
                                shadow = Shadow(
                                    color = Color.Black.copy(alpha = 0.4f),
                                    offset = Offset(0f,8f),
                                    blurRadius = 8f
                                )
                            )
                        )
                    }

                }
            }
            1 -> {
                navController.navigate("reading")
            }
        }

    }

}