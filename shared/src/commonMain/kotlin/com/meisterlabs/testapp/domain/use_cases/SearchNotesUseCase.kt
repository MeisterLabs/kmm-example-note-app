package com.meisterlabs.testapp.domain.use_cases

import com.meisterlabs.noteapp.domain.time.DateTimeUtil
import com.meisterlabs.testapp.domain.model.Note

class SearchNotesUseCase {

    fun execute(notes: List<Note>, query: String): List<Note> {
        if (query.isBlank()) {
            return notes
        }

        return notes.filter {
            it.title.trim().lowercase().contains(query.lowercase()) ||
                it.content.trim().lowercase().contains(query.lowercase())
        }.sortedBy { DateTimeUtil.toEpochMillis(it.created) }
    }
}