package com.rave.noteapp.domain.usecases

import com.rave.noteapp.data.repository.RepositoryImpl
import com.rave.noteapp.domain.models.Note
import com.rave.noteapp.domain.repository.Repository
import javax.inject.Inject

class InsertNoteUseCase @Inject constructor(private val repository: Repository) {
    suspend operator fun invoke(title: String, body: String) {
            repository.insertNote(Note(title = title, body = body))

    }
}