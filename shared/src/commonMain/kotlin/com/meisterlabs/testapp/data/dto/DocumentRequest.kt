package com.meisterlabs.testapp.data.dto

@kotlinx.serialization.Serializable
data class DocumentRequest(
    val fields: NoteRequest,
)