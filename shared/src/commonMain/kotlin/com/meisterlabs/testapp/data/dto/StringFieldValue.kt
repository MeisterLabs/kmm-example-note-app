package com.meisterlabs.testapp.data.dto


// helper class to parse string value from firestore
// TODO - value class??
@kotlinx.serialization.Serializable
data class StringFieldValue(
    val stringValue: String,
)
