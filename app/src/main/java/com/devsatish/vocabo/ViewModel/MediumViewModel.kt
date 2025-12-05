package com.devsatish.vocabo.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.devsatish.vocabo.Model.WordMeanings.MediumRepository

class MediumViewModel : ViewModel() {
    private val repository = MediumRepository()
    private val words = repository.getList()

    private val _clearInput = MutableLiveData(false)
    val clearInput : LiveData<Boolean> = _clearInput

    private val _currentWord = MutableLiveData<String>()
    val currentWord : LiveData<String> = _currentWord

    private val _result = MutableLiveData<String>()
    val result: LiveData<String> = _result

    private var correctMeaning = ""
    private var currentPair: Pair<String, String>? = null

    private val _showHintButton = MutableLiveData(false)
    val showHintButton: LiveData<Boolean> = _showHintButton

    fun loadNewWord() {
        val randomPair = words.random()
        currentPair = randomPair
        _currentWord.value = randomPair.first
        correctMeaning = randomPair.second
    }

    fun resetClearFlag() {
        _clearInput.value = false
    }

    fun refreshWord() {
        currentPair?.let {
            _currentWord.value = it.first
            _showHintButton.value = false
           _clearInput.value = true
        }
    }
    fun wordHinit() {
        currentPair?.let {
            _currentWord.value = it.second
        }
    }

    fun checkAnswer(userAnswer: String) {
        if (userAnswer.trim().equals(correctMeaning, ignoreCase = true)) {
            loadNewWord()
            _result.value = "Correct ✅"
            _showHintButton.value = false
           _clearInput.value = true

        } else {
            _currentWord.value = "Try again!"
            _result.value = "Wrong ❌"
            _showHintButton.value = true
        }
    }
}