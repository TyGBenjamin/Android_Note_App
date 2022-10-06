package com.rave.noteapp.presentation.fragments


import androidx.lifecycle.ViewModel
import com.rave.noteapp.data.repository.RepositoryImpl
import com.rave.noteapp.domain.models.Note
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import androidx.lifecycle.viewModelScope


@HiltViewModel
class EditNoteViewModel @Inject constructor(
    private val repository: RepositoryImpl
): ViewModel() {
    private val _note: MutableStateFlow<Note?> = MutableStateFlow(null)
    val note = _note.asStateFlow()

    fun getNote(id: Int) {
        viewModelScope.launch {
            _note.value = repository.getNoteById(id)
        }
    }

}