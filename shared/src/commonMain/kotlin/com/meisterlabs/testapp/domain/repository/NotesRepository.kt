package com.meisterlabs.testapp.domain.repository

import com.meisterlabs.kmmtasknote.common.Resource
import com.meisterlabs.testapp.domain.model.Note
import kotlinx.coroutines.flow.Flow

interface NotesRepository {
    suspend fun getNotes(): Flow<Resource<List<Note>>>
    suspend fun saveNote(note: Note): Resource<Note>
    suspend fun deleteNote(noteId: String): Resource<Note>
}