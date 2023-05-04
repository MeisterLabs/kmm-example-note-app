package com.meisterlabs.testapp.data.local.di

import com.meisterlabs.testapp.data.local.DatabaseDriverFactory
import com.meisterlabs.testapp.data.local.NoteDataSource
import com.meisterlabs.testapp.data.local.SqlDelightNoteDataSource
import com.meisterlabs.testapp.data.remote.NotesServiceImpl
import com.meisterlabs.testapp.data.repository.NotesRepositoryImpl
import com.meisterlabs.testapp.database.NoteDatabase
import com.meisterlabs.testapp.domain.repository.NotesRepository
import com.meisterlabs.testapp.domain.use_cases.GetNotesUseCase

class DiModule {
    private val factory by lazy { DatabaseDriverFactory() }

    val noteDataSource: NoteDataSource by lazy {
        SqlDelightNoteDataSource(NoteDatabase(factory.createDriver()))
    }

    private val notesRepository: NotesRepository by lazy {
        NotesRepositoryImpl(NotesServiceImpl(), noteDataSource)
    }

    val getNotesUseCase by lazy {
        GetNotesUseCase(notesRepository)
    }
}