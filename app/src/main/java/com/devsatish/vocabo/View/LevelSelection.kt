package com.devsatish.vocabo.View

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.devsatish.vocabo.ui.theme.myGreen
import com.devsatish.vocabo.ui.theme.myOrange
import com.devsatish.vocabo.ui.theme.myRed

@Composable
fun LevelSelect(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .clip(RoundedCornerShape(12))
                .height(60.dp)
                .background(Color(0xFF01B720)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Choose your difficulty",
                fontSize = 28.sp,
                color = Color.White,
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.SemiBold
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Divider(
            color = Color.LightGray,
            thickness = 1.dp,
            modifier = Modifier.padding(vertical = 8.dp)
        )


        Button(
            onClick = {
                navController.navigate("EasyActivity")
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = myGreen,
                contentColor = Color.White
            )
        ) { Text("Easy",
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold
            )}
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = {
                navController.navigate("MediumScreen")
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = myOrange,
                contentColor = Color.White
            )
        ) { Text("Intermediate",
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold)}
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = {
                navController.navigate("HardScreen")
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = myRed,
                contentColor = Color.White
            )
        ) { Text("Advanced",
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold)}

    }
}