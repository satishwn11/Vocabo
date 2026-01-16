package com.devsatish.vocabo.screens.wordScreens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.devsatish.vocabo.viewModel.EasyViewModel

@Composable
fun EasyActivity(viewModel: EasyViewModel = viewModel()) {
    val currentWord by viewModel.currentWord.observeAsState("")
    val result by viewModel.result.observeAsState()
    val showHint by viewModel.showHintButton.observeAsState(false)
    val clearInput by viewModel.clearInput.observeAsState(false)

    var userInput by remember { mutableStateOf("") }
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.loadNewWord()
    }

    if (userInput.isEmpty()) {
        viewModel.refreshWord()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(90.dp)
                .clip(RoundedCornerShape(3.dp))
                .background(color = Color(0xFF00BF06)),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = currentWord,
                fontSize = 36.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }

        TextField(
            value = userInput,
            onValueChange = { userInput = it },
            placeholder = { Text("Type the meaning") },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),

            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    if (userInput == "h") {
                        viewModel.wordHinit()
                    } else {
                        if (userInput.isNotEmpty()) {
                            viewModel.checkAnswer(userInput)
                            Toast.makeText(context, result, Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(context,"Write something",
                                Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            )
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Button(
                onClick = { viewModel.refreshWord() },
                modifier = Modifier.weight(1f)
                    .height(48.dp)
                    .width(60.dp)
                    .border(1.dp, color = Color.Black, CircleShape),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFFFFFF),
                    contentColor = Color.Black
                ),
                shape = RoundedCornerShape(25.dp),
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = 2.dp
                )

            ) { Text("Refresh") }

            Button(
                onClick = {
                   if(userInput == "h") {
                       viewModel.wordHinit()
                   } else {
                       if(userInput.isNotEmpty()) {
                           viewModel.checkAnswer(userInput)
                           Toast.makeText(context, result, Toast.LENGTH_SHORT).show()
                       } else {
                           Toast.makeText(context,"Write something",
                               Toast.LENGTH_SHORT).show()
                       }
                   }
                },
                modifier = Modifier.weight(1f)
                    .height(48.dp)
                    .width(60.dp)
                    .border(1.dp, color = Color.Black, CircleShape),
                shape = RoundedCornerShape(25.dp),
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = 2.dp
                )
            ) { Text("Check") }
        }

//        if (showHint) {
//            Button(
//                onClick = { viewModel.wordHinit() },
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(top = 8.dp)
//            ) { Text("Hint") }
//        }

        if(clearInput) {
            userInput = ""
            viewModel.resetClearFlag()
        }

    }

}