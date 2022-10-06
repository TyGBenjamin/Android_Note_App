package com.rave.noteapp.presentation.fragments

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rave.noteapp.data.repository.RepositoryImpl
import com.rave.noteapp.domain.models.Note
import com.rave.noteapp.domain.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewNoteViewModel @Inject constructor(
    private val repository: RepositoryImpl
): ViewModel(){

    private val _note: MutableStateFlow<Note?> = MutableStateFlow(null)
    val note = _note.asStateFlow()

    fun getNote(id: Int) {
        viewModelScope.launch {
            _note.value = repository.getNoteById(id)
        }
    }


}