package com.rave.noteapp.presentation.screens.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rave.noteapp.databinding.FragmentAddNoteBinding
import com.rave.noteapp.domain.models.Note
import com.rave.noteapp.domain.repository.Repository
import com.rave.noteapp.domain.usecases.InsertNoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddNoteViewModel @Inject constructor(private val insertNoteUseCase: InsertNoteUseCase, ) : ViewModel() {
    fun addNote(title: String, body: String) =
        viewModelScope.launch {
            insertNoteUseCase(title,body)
        }
    }
