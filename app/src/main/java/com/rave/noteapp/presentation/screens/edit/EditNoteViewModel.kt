package com.rave.noteapp.presentation.screens.edit

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rave.noteapp.domain.models.Note
import com.rave.noteapp.domain.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditNoteViewModel @Inject constructor(
    private val repository: Repository,
    private val state: SavedStateHandle
): ViewModel() {
    private val _note: MutableStateFlow<Note?> = MutableStateFlow(null)
    val note = _note.asStateFlow()

//    fun setNote() {
//        val id = state.get<Int>("id") ?: 0
//        viewModelScope.launch {
//            _note.value = repository.getNoteById(id)
//        }
//    }
    fun getNote(id: Int) {
        viewModelScope.launch {
            _note.value = repository.getNoteById(id)
        }
    }

    fun update(note:Note){
        viewModelScope.launch {
            repository.update(note)
        }
    }


}