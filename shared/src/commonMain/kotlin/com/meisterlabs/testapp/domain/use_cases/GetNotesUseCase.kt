package com.meisterlabs.testapp.domain.use_cases

import com.meisterlabs.kmmtasknote.common.Resource
import com.meisterlabs.testapp.domain.model.Note
import com.meisterlabs.testapp.domain.repository.NotesRepository
import kotlinx.coroutines.flow.Flow

class GetNotesUseCase(
    private val repository: NotesRepository
) {

    suspend fun execute(): Flow<Resource<List<Note>>> = repository.getNotes()
}