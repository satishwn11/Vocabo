package com.devsatish.vocabo.screens

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.devsatish.vocabo.viewModel.NoteViewModel
import androidx.core.content.edit
import com.devsatish.vocabo.utils.autoCapitalizeSentences
import kotlinx.coroutines.delay

@Composable
fun WriteScreen(navController: NavController, viewModel: NoteViewModel = viewModel()) {
    val context = LocalContext.current
    val focusManager = LocalFocusManager.current

    val myPrefs = context.getSharedPreferences("MYSharedPrf", Context.MODE_PRIVATE)
    var note by remember { mutableStateOf(myPrefs.getString("savedtext1", "") ?: "") }

    LaunchedEffect(note) {
        delay(400)
        myPrefs.edit {
            putString("savedtext1", note)
        }
    }
        Column(Modifier.padding(8.dp)) {
            TextField(
                value = note,
                onValueChange = { newText ->
                    note = autoCapitalizeSentences(newText)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(350.dp),
                shape = RoundedCornerShape(16.dp),
                textStyle = TextStyle(
                    fontSize = 20.sp
                ),
                placeholder = { Text("Write Something..",
                    fontSize = 18.sp) }
            )

            Spacer(Modifier.height(4.dp))
            Button(onClick = {
                if (note.trim().isNotEmpty()) {
                    viewModel.addnote(note)
                    note = ""

                    focusManager.clearFocus()
                    navController.popBackStack()

                    myPrefs.edit { putString("savedtext1", "") }
                    Toast.makeText(context,"Saved", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, "Write Something first..",
                        Toast.LENGTH_SHORT).show()
                }
            }) { Text("Save Note") }
        }
}
