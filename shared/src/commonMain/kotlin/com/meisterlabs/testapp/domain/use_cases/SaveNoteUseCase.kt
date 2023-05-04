package com.meisterlabs.testapp.domain.use_cases

import com.meisterlabs.kmmtasknote.common.Resource
import com.meisterlabs.testapp.domain.model.Note
import com.meisterlabs.testapp.domain.repository.NotesRepository

class SaveNoteUseCase(
    private val repository: NotesRepository,
) {
    suspend fun execute(note: Note): Resource<Note> = repository.saveNote(note)
}