package com.devsatish.vocabo.screens

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.devsatish.vocabo.viewModel.NoteViewModel
import androidx.core.content.edit
import com.devsatish.vocabo.R
import com.devsatish.vocabo.ui.theme.myGreen
import com.devsatish.vocabo.utils.autoCapitalizeSentences
import com.devsatish.vocabo.utils.wordcounter
import kotlinx.coroutines.delay

@Composable
fun WriteScreen(navController: NavController, viewModel: NoteViewModel = viewModel()) {
    val robotoLight = FontFamily(
        Font(R.font.robotolight)
    )

    val context = LocalContext.current
    val focusManager = LocalFocusManager.current

    val myPrefs = context.getSharedPreferences("MYSharedPrf", Context.MODE_PRIVATE)
    var note by remember { mutableStateOf(myPrefs.getString("savedtext1", "") ?: "") }
    val words = wordcounter(note)

    LaunchedEffect(note) {
        delay(400)
        myPrefs.edit {
            putString("savedtext1", note)
        }
    }
        Column(Modifier.padding(8.dp)) {

            // Text Field
            TextField(
                value = note,
                onValueChange = { newText ->
                    note = autoCapitalizeSentences(newText)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(350.dp),
                shape = RoundedCornerShape(8.dp),
                textStyle = TextStyle(
                    fontSize = 20.sp,
                    lineHeight = 28.sp,
                    fontFamily = FontFamily.SansSerif
                ),
                placeholder = {
                    Text(
                        text = "Write something...",
                        fontSize = 18.sp,
                        color = Color.Gray
                    )
                },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color(0xFFEDEDED),
                    unfocusedContainerColor = Color(0xFFEDEDED),
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    cursorColor = Color.Blue
                ),
                        maxLines = Int.MAX_VALUE,
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.Sentences
                )
            )

            Row(
                modifier = Modifier.fillMaxWidth()
                    .padding(4.dp),
                horizontalArrangement = Arrangement.End
            ) {
                Text(
                    text = "Words: $words",
                    color = Color.Blue
                )
            }

            Spacer(Modifier.height(12.dp))

            Button(
                onClick = {
                    if (note.trim().isNotEmpty()) {
                        viewModel.addnote(note)
                        note = ""

                        focusManager.clearFocus()
                        navController.popBackStack()

                        myPrefs.edit { putString("savedtext1", "") }
                        Toast.makeText(context, "Saved", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(
                            context,
                            "Write something first..",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = myGreen,
                    contentColor = Color.White
                ),
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = 6.dp,
                    pressedElevation = 2.dp
                )
            ) {
                Text(
                    text = "Save Note",
                    fontSize = 18.sp
                )
            }

        }
}
