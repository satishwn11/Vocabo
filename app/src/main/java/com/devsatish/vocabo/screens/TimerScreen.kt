package com.devsatish.vocabo.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.devsatish.vocabo.model.SessionEntity

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimerScreen(
    elapsedTime: Long,
    sessions: List<SessionEntity>,
    grouped: Map<String, Long>
) {
        val totalSeconds = elapsedTime / 1000
        val minutes = totalSeconds / 60
        val seconds = totalSeconds % 60

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(Modifier.height(20.dp))

        // ⏱ Timer Box
        Box(
            modifier = Modifier
                .width(250.dp)
                .height(110.dp)
                .shadow(
                    elevation = 6.dp,
                    shape = RoundedCornerShape(20.dp)
                )
                .background(Color(0xFFF4F4F4), RoundedCornerShape(20.dp)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                "$minutes : $seconds",
                fontSize = 60.sp,
                fontWeight = FontWeight.ExtraBold
            )
        }

        Spacer(Modifier.height(20.dp))

        // Title
        Text(
            "Past Records",
            fontSize = 38.sp,
            fontWeight = FontWeight.Black,
            color = Color.Gray
        )

        Spacer(Modifier.height(10.dp))

        // List
        LazyColumn {
            items(grouped.entries.toList()) { (date, totalDuration) ->
                val totalSeconds = totalDuration / 1000
                val minutes = totalSeconds / 60
                val seconds = totalSeconds % 60

                // 🟦 New Card Style
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFFDDEEFF)  // Light Blue
                    ),
                    shape = RoundedCornerShape(16.dp),
                    elevation = CardDefaults.cardElevation(6.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {

                        Row {
                            Text(
                                text = "DATE - ",
                                fontSize = 18.sp,
                                color = Color(0xFF0B2545),
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = date,
                                fontSize = 24.sp,
                                color = Color(0xFF0B2545),
                                fontWeight = FontWeight.Black
                            )
                        }

                        Spacer(modifier = Modifier.height(6.dp))

                        Row {
                            Text(
                                text = "TIME SPENT - ",
                                fontSize = 18.sp,
                                color = Color(0xFF0B2545),
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = "$minutes min $seconds sec",
                                fontSize = 24.sp,
                                color = Color(0xFF0B2545),
                                fontWeight = FontWeight.Black
                            )
                        }
                    }
                }
            }
        }
    }
}