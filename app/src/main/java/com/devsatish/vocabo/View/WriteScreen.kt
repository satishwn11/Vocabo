package com.devsatish.vocabo.View

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.devsatish.vocabo.ViewModel.NoteViewModel
import androidx.core.content.edit

@Composable
fun WriteScreen(navController: NavController, viewModel: NoteViewModel = viewModel()) {
    val context = LocalContext.current

    val myPrefs = context.getSharedPreferences("MYSharedPrf",
        Context.MODE_PRIVATE)

    var note by remember { mutableStateOf(
        myPrefs.getString("savedtext1", "") ?: "") }

    Column {
        Column(Modifier.padding(8.dp)) {
            TextField(
                value = note,
                onValueChange = { note = it
                 // Saving live typing text
                 myPrefs.edit { putString("savedtext1", it) }
                                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(350.dp),
                placeholder = { Text("Write Something..") }
            )

            Spacer(Modifier.height(4.dp))
            Button(onClick = {
                if (note.trim().isNotEmpty()) {
                    viewModel.addnote(note)
                    note = ""
                    myPrefs.edit { putString("savedtext1", "") }
                    navController.popBackStack()
                } else {
                    Toast.makeText(context, "Write Something first..",
                        Toast.LENGTH_SHORT).show()
                }
            }) { Text("Save Note") }
        }
    }
}