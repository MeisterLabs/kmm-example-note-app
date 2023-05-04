package com.meisterlabs.testapp.data.remote

import com.meisterlabs.testapp.common.Constants
import com.meisterlabs.testapp.data.dto.CollectionResponse
import com.meisterlabs.testapp.data.dto.NoteRequest
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.get
import io.ktor.client.request.url
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.http.isSuccess
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class NotesServiceImpl : NotesService {

    // TODO - DI
    private val client by lazy {
        HttpClient {
            install(Logging) {
                level = LogLevel.ALL
            }

            install(ContentNegotiation) {
                json(Json { isLenient = true; ignoreUnknownKeys = true })
            }
        }
    }

    override suspend fun getNotes(): CollectionResponse? {
        val response = client.get {
            url(Constants.USER_NOTES_COLLECTION)
            contentType(ContentType.Application.Json)
        }

        return if (response.status.isSuccess()) {
            response.body()
        } else {
            null
        }
    }

    override suspend fun createNote(noteRequest: NoteRequest) {
        TODO("Not yet implemented")
    }
}