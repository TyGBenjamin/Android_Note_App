package com.rave.noteapp.data.local

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import com.rave.noteapp.domain.models.Note
import kotlinx.coroutines.flow.Flow


@Dao
interface NoteDao {

    @Query("SELECT * FROM notes")
    fun getNotes(): Flow<List<Note>>

    @Query("select * from notes where id = :id")
    suspend fun getNoteById(id:Int): Note?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: Note)

    @Update
    fun update(note: Note)

    @Delete
    suspend fun deleteNote(note:Note)

}