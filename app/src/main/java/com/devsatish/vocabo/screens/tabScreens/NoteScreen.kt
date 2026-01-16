package com.devsatish.vocabo.screens.tabScreens

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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.devsatish.vocabo.R
import com.devsatish.vocabo.utils.formatDate
import com.devsatish.vocabo.utils.formatTime
import com.devsatish.vocabo.viewModel.NoteViewModel

@Composable
fun NoteScreen(navController: NavController, viewModel: NoteViewModel = viewModel()) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate("WriteScreen")
                }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.pencil),
                    contentDescription = "New Note",
                    Modifier.size(30.dp),
                    tint = Color.Black
                )
            }
        }

    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(8.dp)
        ) {
            LazyColumn {
                items(viewModel.notes) { item ->

                    var expanded by remember { mutableStateOf(false) }

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .combinedClickable(
                                onClick = {
                                    // normal click -> open note or edit (optional)
                                },
                                onLongClick = {
                                    expanded = true
                                }
                            )
                            .shadow(6.dp, shape = RoundedCornerShape(12.dp)),
                        colors = CardDefaults.cardColors(
                            containerColor = Color(0xFFDCEBFF)
                        )
                    ) {

                        Text(
                            item.text,
                            fontSize = 18.sp,
                            fontFamily = FontFamily.SansSerif,
                            fontWeight = FontWeight.Medium,
                            modifier = Modifier.padding(8.dp)
                        )

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(text = formatDate(item.timestamp))
                            Text(text = formatTime(item.timestamp))
                        }

                            // ---- DROPDOWN MENU ----
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
                    Spacer(Modifier.height(12.dp))

                }
            }

        }
    }
}