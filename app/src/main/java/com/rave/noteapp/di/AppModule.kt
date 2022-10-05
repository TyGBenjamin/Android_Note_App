package com.rave.noteapp.di

import android.content.Context
import androidx.room.Room
import com.rave.noteapp.data.local.NoteDao
import com.rave.noteapp.data.local.NoteDatabase
import com.rave.noteapp.data.repository.RepositoryImpl
import com.rave.noteapp.domain.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    fun providesRoomDB(@ApplicationContext applicationContext: Context): NoteDao = Room.databaseBuilder(
            applicationContext,
            NoteDatabase::class.java, "note-database"
        ).build().noteDao()

    @Provides
    fun providesRepo(noteDao:NoteDao) : Repository = RepositoryImpl(noteDao)
}