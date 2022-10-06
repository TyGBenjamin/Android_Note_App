package com.rave.noteapp.presentation.screens.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.rave.noteapp.adapters.NoteAdapter
import com.rave.noteapp.data.local.NoteDao
import com.rave.noteapp.data.repository.RepositoryImpl
import com.rave.noteapp.domain.models.Note
import com.rave.noteapp.domain.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailNoteViewModel @Inject constructor(
    private val repository: Repository,
    private val state: SavedStateHandle
    ): ViewModel() {
    private val _note: MutableStateFlow<Note?> = MutableStateFlow(null)
    val note = _note.asStateFlow()

    fun setNote() {
        val id = state.get<Int>("id") ?: 0
        viewModelScope.launch {
            _note.value = repository.getNoteById(id)
        }
    }


}