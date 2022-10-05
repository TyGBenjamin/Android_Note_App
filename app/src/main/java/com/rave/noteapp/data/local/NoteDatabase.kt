package com.rave.noteapp.data.local

import androidx.room.Database
import androidx.room.Entity
import androidx.room.Room
import androidx.room.RoomDatabase
import com.rave.noteapp.domain.models.Note

@Database(entities = [Note::class], version = 1 )
abstract class NoteDatabase : RoomDatabase() {
    abstract fun noteDao() : NoteDao
}