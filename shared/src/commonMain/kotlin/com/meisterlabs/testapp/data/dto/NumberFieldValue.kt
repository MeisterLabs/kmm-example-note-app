package com.meisterlabs.testapp.data.dto

import kotlin.jvm.JvmInline
import kotlinx.serialization.SerialName

// helper class to parse number value from firestore
// TODO - value class??
@kotlinx.serialization.Serializable
data class NumberFieldValue(
    val integerValue: Long,
)
