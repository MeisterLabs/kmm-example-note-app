package com.meisterlabs.testapp.android.di

import android.app.Application
import com.meisterlabs.testapp.domain.note.NoteDataSource
import com.meisterlabs.testapp.data.local.DatabaseDriverFactory
import com.meisterlabs.testapp.data.note.SqlDelightNoteDataSource
import com.meisterlabs.testapp.database.NoteDatabase
import com.squareup.sqldelight.db.SqlDriver
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideSqlDriver(app: Application): SqlDriver {
        return DatabaseDriverFactory(app).createDriver()
    }

    @Provides
    @Singleton
    fun provideNoteDataSource(driver: SqlDriver): NoteDataSource {
        // if more sources, create instance of note database
        return SqlDelightNoteDataSource(NoteDatabase(driver))
    }
}