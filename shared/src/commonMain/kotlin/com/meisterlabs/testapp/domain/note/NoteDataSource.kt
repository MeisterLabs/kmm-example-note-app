package com.meisterlabs.testapp.domain.note

interface NoteDataSource {
    suspend fun insertNode(note: Note)
    suspend fun getNoteById(id: Long): Note?
    suspend fun getAllNotes(): List<Note>
    suspend fun deleteNoteByd(id: Long)
}