package com.meisterlabs.testapp.data.remote

import com.meisterlabs.testapp.data.dto.CollectionResponse
import com.meisterlabs.testapp.data.dto.NoteRequest

interface NotesService {

    suspend fun getNotes(): CollectionResponse?

    suspend fun createNote(noteRequest: NoteRequest)
}