package com.rave.noteapp.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import androidx.room.Update
import com.rave.noteapp.domain.models.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {
    @Query
        ("SELECT * FROM notes WHERE title LIKE '%' || :search || '%'")
    fun getNotes(search: String?): Flow<List<Note>>

    @Query
        ("SELECT * FROM notes WHERE id in (:id)")
    fun getNoteById(id: Int) : Note

    @Insert(onConflict = REPLACE)
     fun insertNote (note:Note)

     @Update
     fun update(note: Note)

    @Delete
    fun deleteNote(note:Note)

//
//    SELECT * FROM Customers
//    ORDER BY Country DESC;

}