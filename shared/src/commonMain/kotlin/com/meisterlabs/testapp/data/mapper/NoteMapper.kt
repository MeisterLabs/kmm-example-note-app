package com.meisterlabs.testapp.data.mapper

import com.meisterlabs.noteapp.domain.time.DateTimeUtil
import com.meisterlabs.testapp.data.dto.DocumentResponse
import com.meisterlabs.testapp.domain.model.Note
import database.NoteEntity
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDateTime
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

fun DocumentResponse.toNote(): Note = Note(
    id = name.substringAfterLast("/"),
    title = fields.name.stringValue,
    content = fields.content.stringValue,
    colorHex = 0L,
    created = DateTimeUtil.now()
)
