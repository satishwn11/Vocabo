package com.devsatish.vocabo.screens.tabScreens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.devsatish.vocabo.R
import com.devsatish.vocabo.ui.theme.myGreen
import com.devsatish.vocabo.utils.formatDate
import com.devsatish.vocabo.utils.formatTime
import com.devsatish.vocabo.viewModel.NoteViewModel


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NoteScreen(
    navController: NavController,
    viewModel: NoteViewModel = viewModel()
) {

    Scaffold(
        containerColor = Color(0xFFF5F6FA),
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate("WriteScreen") },
                containerColor = myGreen
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.pencil_edit),
                    contentDescription = "New Note",
                    modifier = Modifier.size(24.dp),
                    tint = Color.White
                )
            }
        }
    ) { paddingValues ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            items(viewModel.notes) { item ->

                var expanded by remember { mutableStateOf(false) }

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .combinedClickable(
                            onClick = { },
                            onLongClick = { expanded = true }
                        ),
                    shape = RoundedCornerShape(18.dp),
                    elevation = CardDefaults.cardElevation(8.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    )
                ) {

                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {

                        Text(
                            text = item.text,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Medium,
                            color = Color.Black
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = formatDate(item.timestamp),
                                fontSize = 14.sp,
                                color = Color.Gray
                            )
                            Text(
                                text = formatTime(item.timestamp),
                                fontSize = 14.sp,
                                color = Color.Gray
                            )
                        }
                    }

                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        DropdownMenuItem(
                            text = { Text("Delete") },
                            onClick = {
                                expanded = false
                                viewModel.deletenote(item)
                            }
                        )
                    }
                }
            }
        }
    }
}
