package com.meisterlabs.testapp.android.di

import android.app.Application
import com.meisterlabs.testapp.data.local.NoteDataSource
import com.meisterlabs.testapp.data.local.DatabaseDriverFactory
import com.meisterlabs.testapp.data.repository.NotesRepositoryImpl
import com.meisterlabs.testapp.data.local.SqlDelightNoteDataSource
import com.meisterlabs.testapp.data.remote.NotesService
import com.meisterlabs.testapp.data.remote.NotesServiceImpl
import com.meisterlabs.testapp.database.NoteDatabase
import com.meisterlabs.testapp.domain.repository.NotesRepository
import com.meisterlabs.testapp.domain.use_cases.DeleteNoteUseCase
import com.meisterlabs.testapp.domain.use_cases.GetNotesUseCase
import com.meisterlabs.testapp.domain.use_cases.SaveNoteUseCase
import com.meisterlabs.testapp.domain.use_cases.SearchNotesUseCase
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

    @Provides
    @Singleton
    fun provideNoteRepository(
        notesService: NotesService,
        noteDataSource: NoteDataSource,
    ): NotesRepository {
        return NotesRepositoryImpl(notesService, noteDataSource)
    }

    @Provides
    @Singleton
    fun provideNoteService(): NotesService {
        return NotesServiceImpl()
    }

    @Provides
    @Singleton
    fun provideGetNotesUseCase(repository: NotesRepository): GetNotesUseCase {
        return GetNotesUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideDeleteNoteUseCase(repository: NotesRepository): DeleteNoteUseCase {
        return DeleteNoteUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideSearchNotesUseCase(): SearchNotesUseCase {
        return SearchNotesUseCase()
    }

    @Provides
    @Singleton
    fun provideSaveNoteUseCase(repository: NotesRepository): SaveNoteUseCase {
        return SaveNoteUseCase(repository)
    }
}