package com.meisterlabs.testapp.data.local

import android.content.Context
import com.meisterlabs.testapp.database.NoteDatabase
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import database.NoteQueries

// we need context -> that's why android repo
actual class DatabaseDriverFactory(
    private val context: Context,
) {
    actual fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(NoteDatabase.Schema, context)
    }
}