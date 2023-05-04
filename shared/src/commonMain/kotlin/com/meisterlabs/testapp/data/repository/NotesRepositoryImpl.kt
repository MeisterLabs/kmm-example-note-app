package com.meisterlabs.testapp.data.repository

import com.meisterlabs.kmmtasknote.common.Resource
import com.meisterlabs.testapp.data.mapper.toNote
import com.meisterlabs.testapp.data.remote.NotesService
import com.meisterlabs.testapp.domain.model.Note
import com.meisterlabs.testapp.domain.repository.NotesRepository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class NotesRepositoryImpl(
    private val notesService: NotesService,
) : NotesRepository {

    override suspend fun getNotes(): Flow<Resource<List<Note>>> = flow {
         try {
            emit(Resource.Loading())
            val notes = notesService.getNotes()?.documents?.map { it.toNote() } ?: emptyList()
            emit(Resource.Success(notes))
        } catch (e: Exception) {
            emit(Resource.Error("${e.message}"))
        }

    }

    override suspend fun addNote(name: String, content: String): Resource<Note> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteNote(noteId: String): Resource<Note> {
        TODO("Not yet implemented")
    }
}