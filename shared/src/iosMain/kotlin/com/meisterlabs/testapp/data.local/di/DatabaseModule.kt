package com.meisterlabs.testapp.data.local.di

import com.meisterlabs.testapp.data.local.NoteDataSource
import com.meisterlabs.testapp.data.local.DatabaseDriverFactory
import com.meisterlabs.testapp.data.local.SqlDelightNoteDataSource
import com.meisterlabs.testapp.database.NoteDatabase

class DatabaseModule {

    private val factory by lazy { DatabaseDriverFactory() }
    val noteDataSource: NoteDataSource by lazy {
        SqlDelightNoteDataSource(NoteDatabase(factory.createDriver()))
    }
}