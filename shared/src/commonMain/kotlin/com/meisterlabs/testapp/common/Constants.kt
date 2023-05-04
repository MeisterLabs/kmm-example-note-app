package com.meisterlabs.testapp.common

object Constants {
    private const val BASE_URL =
        "https://firestore.googleapis.com/v1/projects/kmm-meisterlabs-note-app/databases/(default)/documents"
    // all users notes
    // TODO - remove hardcorded userId
    private const val USERS_COLLECTION = "${BASE_URL}/users/FKK5xUlVsrRNhGh8nr74SDU6RFr1"
    // user's notes
    const val USER_NOTES_COLLECTION = "$USERS_COLLECTION/notes"
    const val NOTE_NAME = "name"
    const val NOTE_CONTENT = "content"
}