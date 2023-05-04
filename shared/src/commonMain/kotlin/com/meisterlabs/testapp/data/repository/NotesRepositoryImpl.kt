package com.meisterlabs.testapp.data.repository

import com.meisterlabs.kmmtasknote.common.Resource
import com.meisterlabs.noteapp.domain.time.DateTimeUtil
import com.meisterlabs.testapp.data.dto.NoteRequest
import com.meisterlabs.testapp.data.dto.NumberFieldValue
import com.meisterlabs.testapp.data.dto.StringFieldValue
import com.meisterlabs.testapp.data.local.NoteDataSource
import com.meisterlabs.testapp.data.mapper.toNote
import com.meisterlabs.testapp.data.remote.NotesService
import com.meisterlabs.testapp.domain.model.Note
import com.meisterlabs.testapp.domain.repository.NotesRepository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class NotesRepositoryImpl(
    private val notesService: NotesService,
    private val noteDataSource: NoteDataSource,
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

    override suspend fun saveNote(note: Note): Resource<Note> = try {
        val noteRequest = NoteRequest(
            name = StringFieldValue(note.title),
            content = StringFieldValue(note.content),
            colorHex = NumberFieldValue(note.colorHex),
            createdAt = NumberFieldValue(DateTimeUtil.toEpochMillis(DateTimeUtil.now()))
        )

        notesService.createNote(noteRequest)?.toNote()?.let { savedNote ->
            noteDataSource.insertNode(savedNote)
            Resource.Success(savedNote)
        } ?: Resource.Error("Note shouldn't be null")
    } catch (e: Exception) {
        Resource.Error("Error create note: $e")
    }

    override suspend fun deleteNote(noteId: String): Resource<Unit> = try {
        notesService.deleteNote(noteId)
        noteDataSource.deleteNoteByd(noteId)
        Resource.Success(Unit)
    } catch (e: Exception) {
        Resource.Error("Failed to delete note: $e")
    }
}