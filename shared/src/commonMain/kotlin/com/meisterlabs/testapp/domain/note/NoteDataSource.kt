package com.meisterlabs.testapp.domain.note

import com.meisterlabs.testapp.domain.model.Note

interface NoteDataSource {
    suspend fun insertNode(note: Note)
    suspend fun getNoteById(id: Long): Note?
    suspend fun getAllNotes(): List<Note>
    suspend fun deleteNoteByd(id: Long)
}