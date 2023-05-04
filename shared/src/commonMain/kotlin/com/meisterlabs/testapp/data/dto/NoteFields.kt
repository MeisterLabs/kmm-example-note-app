package com.meisterlabs.testapp.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class NoteFields(
    val name: StringFieldValue,
    val content: StringFieldValue
)
