package com.meisterlabs.testapp.domain.use_cases

import com.meisterlabs.testapp.domain.repository.NotesRepository

class DeleteNoteUseCase(
    private val repository: NotesRepository
) {

    suspend fun execute(noteId: String) = repository.deleteNote(noteId)
}