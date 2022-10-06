package com.rave.noteapp.domain.repository

import com.rave.noteapp.domain.models.Note
import kotlinx.coroutines.flow.Flow

interface Repository {
    fun getNotes(search: String?): Flow<List<Note>>
    suspend fun getNoteById(id: Int): Note
    suspend fun deleteNote(note: Note)
    suspend fun insertNote(note: Note)
    suspend fun deleteAllNotes()
    fun update(note: Note)
}