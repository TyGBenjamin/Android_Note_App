package com.rave.noteapp.presentation.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rave.noteapp.domain.models.Note
import com.rave.noteapp.domain.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
    val noteList = repository.getNotes()

    fun insertNote() {
        viewModelScope.launch {
            repository.insertNote(Note(id =4, title = "NEWNOTE", body = "This is an example of a different note my guy"))
        }
    }

    fun deleteNote(note: Note) = viewModelScope.launch {
        repository.deleteNote(note)
    }


    companion object {
        const val TAG = "HOMEVIEWMODEL"
    }

//    private val _viewState : Flow<List<Note>>
//    val viewState : Flow<List<Note>> = _viewState
//
//
//    private fun getAllNotes() = viewModelScope.launch {
//        _viewState.collectLatest() = repository.getNotes()
//    }
}