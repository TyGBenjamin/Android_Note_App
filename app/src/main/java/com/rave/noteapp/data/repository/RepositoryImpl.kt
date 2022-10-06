package com.rave.noteapp.data.repository

import com.rave.noteapp.data.local.NoteDao
import com.rave.noteapp.domain.models.Note
import com.rave.noteapp.domain.repository.Repository
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val noteDao: NoteDao
): Repository {
    override fun getNotes(search: String?): Flow<List<Note>> {
    return noteDao.getNotes(search)
    }

    override suspend fun getNoteById(id:Int): Note = withContext(Dispatchers.IO) {
        noteDao.getNoteById(id)
    }

    override suspend fun insertNote(note:Note) = withContext(Dispatchers.IO) {
        noteDao.insertNote(note)


    }

    override fun update(note: Note){
        CoroutineScope(Dispatchers.IO).launch {
            noteDao.update(note)
        }

    }

    override suspend fun deleteAllNotes() = withContext(Dispatchers.IO) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteNote(note:Note) = withContext(Dispatchers.IO) {
        noteDao.deleteNote(note)

    }
}