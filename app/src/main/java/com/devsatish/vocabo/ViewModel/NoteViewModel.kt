package com.devsatish.vocabo.ViewModel

import android.app.Application
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.devsatish.vocabo.Repository.NoteDatabase
import com.devsatish.vocabo.Repository.Notes
import kotlinx.coroutines.launch

class NoteViewModel(application: Application): AndroidViewModel(application) {

    private val dao = NoteDatabase.getDatabase(application).noteDao()

    var notes = mutableStateListOf<Notes>()
        private set

    init {
        viewModelScope.launch {
            dao.getAllnotes().collect { list ->
                notes.clear()
                notes.addAll(list)
            }
        }
    }

    fun addnote(text: String) {
        viewModelScope.launch {
            val note = Notes(
                text = text,
                timestamp = System.currentTimeMillis()
            )
            dao.addnotes(note)
        }
    }
}