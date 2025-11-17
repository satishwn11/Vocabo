package com.devsatish.vocabo.View

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.devsatish.vocabo.R
import com.devsatish.vocabo.ViewModel.NoteViewModel

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
                   painter = painterResource(id = R.drawable.edit_icon),
                   contentDescription = "Add Note",
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
                items(viewModel.notes) {  item ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(6.dp),
                        elevation = CardDefaults.cardElevation(4.dp)
                    ) {
                        Text(item.text,
                            modifier = Modifier
                                .padding(8.dp)
                        )
                    }
                }
            }

        }
    }
}