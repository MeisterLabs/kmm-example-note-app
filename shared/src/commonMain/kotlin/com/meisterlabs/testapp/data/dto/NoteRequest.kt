package com.meisterlabs.testapp.data.dto

@kotlinx.serialization.Serializable
data class NoteRequest(
    val name: StringFieldValue,
    val content: StringFieldValue,
    val colorHex: NumberFieldValue,
    val createdAt: NumberFieldValue,
)
