package com.rave.noteapp

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rave.noteapp.domain.models.Note
import com.rave.noteapp.domain.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {
    fun getNotes() {
        viewModelScope.launch {
            val list = repository.getNotes()
            Log.d(TAG, "getNotes: ${list.first()}")
        }
    }

    fun insertNote() {
        viewModelScope.launch {
            repository.insertNote(
                Note(
                    title = "Knew Nothing!",
                    body = "New note body."
                )
            )
        }
    }

    companion object {
        const val TAG = "ViewModelLogger"
    }
}