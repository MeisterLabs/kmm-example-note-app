package com.meisterlabs.testapp.data.dto

@kotlinx.serialization.Serializable
data class CollectionResponse(
    val documents: List<DocumentResponse>
)
