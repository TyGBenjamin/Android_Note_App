package com.rave.noteapp.presentation.screens.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rave.noteapp.domain.models.Note
import com.rave.noteapp.domain.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
    private val search = MutableStateFlow("")
//    private var _noteList = repository.getNotes(search.value)
//    val noteList = repository.getNotes(search.value)
    lateinit var noteList: Flow<List<Note>>

    init {
        viewModelScope.launch {
            search.collectLatest {
                noteList = repository.getNotes(it)
            }
        }
    }

    fun updateSearchText(searchText: String) {
        Log.d(TAG, "updateSearchText: $searchText")
        viewModelScope.launch {
            Log.d(TAG, "before search: ${noteList.first()}")
        }
        search.value = searchText
//        _noteList = repository.getNotes(searchText)
        viewModelScope.launch {
            Log.d(TAG, "after search: ${noteList.first()}")
        }
    }

    fun insertNote() {
        viewModelScope.launch {
            repository.insertNote(
                Note(
                    id = 4,
                    title = "NEWNOTE",
                    body = "This is an example of a different note my guy"
                )
            )
        }
    }

    fun deleteNote(note: Note) = viewModelScope.launch {
        repository.deleteNote(note)
    }


    companion object {
        const val TAG = "Logger"
    }

//    private val _viewState : Flow<List<Note>>
//    val viewState : Flow<List<Note>> = _viewState
//
//
//    private fun getAllNotes() = viewModelScope.launch {
//        _viewState.collectLatest() = repository.getNotes()
//    }
}