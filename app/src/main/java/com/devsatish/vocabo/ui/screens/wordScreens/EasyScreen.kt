package com.devsatish.vocabo.ui.screens.wordScreens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.devsatish.vocabo.ui.viewModel.EasyViewModel

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

        _root_ide_package_.com.devsatish.vocabo.ui.screens.wordScreens.TextBox(currentWord)

        _root_ide_package_.com.devsatish.vocabo.ui.screens.wordScreens.AnswerInput(
            value = userInput,
            onValueChange = { userInput = it },
            onDone = {
                if (userInput == "h") {
                    viewModel.wordHinit()
                } else if (userInput.isNotEmpty()) {
                    viewModel.checkAnswer(userInput)
                    Toast.makeText(context, result, Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, "Write something", Toast.LENGTH_SHORT).show()
                }
            }
        )

        _root_ide_package_.com.devsatish.vocabo.ui.screens.wordScreens.ActionButtons(
            onRefresh = { viewModel.refreshWord() },
            onCheck = {
                if (userInput == "h") {
                    viewModel.wordHinit()
                } else if (userInput.isNotEmpty()) {
                    viewModel.checkAnswer(userInput)
                    Toast.makeText(context, result, Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, "Write something", Toast.LENGTH_SHORT).show()
                }
            }
        )

        if(clearInput) {
            userInput = ""
            viewModel.resetClearFlag()
        }

    }

}
