package com.meisterlabs.testapp.data.mapper

import com.meisterlabs.testapp.data.dto.DocumentResponse
import com.meisterlabs.testapp.domain.model.Note
import database.NoteEntity
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

fun NoteEntity.toNote(): Note = Note(
    id = id,
    title = title,
    content = content,
    colorHex = colorHex,
    created = Instant
        .fromEpochMilliseconds(created)
        .toLocalDateTime(TimeZone.currentSystemDefault())
)

fun DocumentResponse.toNote(): Note = with(fields) {
    Note(
        id = this@toNote.name.substringAfterLast("/"),
        title = name.stringValue,
        content = content.stringValue,
        colorHex = colorHex.integerValue,
        created = Instant
            .fromEpochMilliseconds(createdAt.integerValue)
            .toLocalDateTime(TimeZone.currentSystemDefault())
    )
}
