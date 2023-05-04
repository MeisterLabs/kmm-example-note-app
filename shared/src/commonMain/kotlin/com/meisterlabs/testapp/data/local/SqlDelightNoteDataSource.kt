package com.meisterlabs.testapp.data.local

import com.meisterlabs.testapp.domain.model.Note
import com.meisterlabs.testapp.domain.note.NoteDataSource
import com.meisterlabs.noteapp.domain.time.DateTimeUtil
import com.meisterlabs.testapp.data.mapper.toNote
import com.meisterlabs.testapp.database.NoteDatabase

class SqlDelightNoteDataSource(
    db: NoteDatabase,
) : NoteDataSource {

    private val noteQueries = db.noteQueries

    override suspend fun insertNode(note: Note) {
        noteQueries.insertNote(
            id = note.id ?: "",
            title = note.title,
            content = note.content,
            colorHex = note.colorHex,
            created = DateTimeUtil.toEpochMillis(note.created)
        )
    }

    override suspend fun getNoteById(id: String): Note? {
        return noteQueries
            .getNoteById(id)
            .executeAsOneOrNull()
            ?.toNote()
    }

    override suspend fun getAllNotes(): List<Note> {
        return noteQueries
            .getAllNotes()
            .executeAsList()
            .map { it.toNote() }
    }

    override suspend fun deleteNoteByd(id: String) {
        noteQueries.deleteNoteById(id)
    }
}