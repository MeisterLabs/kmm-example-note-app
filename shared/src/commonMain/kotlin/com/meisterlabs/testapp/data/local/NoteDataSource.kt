package com.meisterlabs.testapp.data.local

import com.meisterlabs.testapp.domain.model.Note

interface NoteDataSource {
    suspend fun insertNode(note: Note)
    suspend fun getNoteById(id: String): Note?
    suspend fun getAllNotes(): List<Note>
    suspend fun deleteNoteByd(id: String)
}