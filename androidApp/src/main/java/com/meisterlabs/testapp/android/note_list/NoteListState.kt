package com.meisterlabs.testapp.android.note_list

import com.meisterlabs.testapp.domain.model.Note

data class NoteListState(
    val notes: List<Note> = emptyList(),
    val searchText: String = "",
    val isSearchActive: Boolean = false,
)
