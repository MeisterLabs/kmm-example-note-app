package com.meisterlabs.testapp.data.repository

import com.meisterlabs.kmmtasknote.common.Resource
import com.meisterlabs.testapp.common.Constants
import com.meisterlabs.testapp.domain.model.Note
import com.meisterlabs.testapp.domain.repository.NotesRepository
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.firestore.firestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class NotesRepositoryImpl: NotesRepository {

    override suspend fun getNotes(): Flow<Resource<List<Note>>> = callbackFlow {
        // trySend(Resource.Loading<Note>
        val firestore = Firebase.firestore.collection(Constants.NOTES_COLLECTION).snapshots
        // val snapshotListener = noteRef.addSnapshotListener { snapshot, e ->
        //     val notesResponse = if (snapshot != null) {
        //         val notes = snapshot.toObjects(Note::class)
        //         Resource.Success(notes)
        //     } else {
        //         Resource.Error("Error", e)
        //     }
        //     trySend(notesResponse)
        // }
        // awaitClose {
        //     snapshotListener.remove()
        // }
    }

    override suspend fun addNote(name: String, content: String): Resource<Note> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteNote(noteId: String): Resource<Note> {
        TODO("Not yet implemented")
    }
}