package com.rave.noteapp.domain.models

import android.icu.text.CaseMap.Title
import androidx.annotation.ColorInt
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes") // TABLE IN OUR DATABASE AND IT IS HOW WE MAP OUR OBJECTS INTO OUR DATABASE TABLES
data class Note(
    @PrimaryKey(autoGenerate = true) // AUTO GENERATES KEY YOU NEED TO MAKE ID = 0
    val id: Int = 0,
    @ColumnInfo
    val title: String,
    @ColumnInfo
    val body: String,
)
