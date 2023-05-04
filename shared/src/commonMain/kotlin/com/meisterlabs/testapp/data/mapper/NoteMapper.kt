package com.meisterlabs.testapp.data.mapper

import com.meisterlabs.testapp.domain.model.Note
import database.NoteEntity
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

fun NoteEntity.toNote(): Note = Note(
    id = id,
    remoteId = remoteId,
    title = title,
    content = content,
    colorHex = colorHex,
    created = Instant
        .fromEpochMilliseconds(created)
        .toLocalDateTime(TimeZone.currentSystemDefault())
)
