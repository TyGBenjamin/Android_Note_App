package com.rave.noteapp.domain.repository

import com.rave.noteapp.domain.models.Note
import kotlinx.coroutines.flow.Flow

interface Repository {
    fun getNotes(): Flow<List<Note>>
    suspend fun getNoteById(id: Int): Note?
    suspend fun insertNote(note: Note)
     fun update(note: Note)
    suspend fun deleteNote(note: Note)

}