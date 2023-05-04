package com.meisterlabs.testapp.android.note_detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.meisterlabs.kmmtasknote.common.Resource
import com.meisterlabs.testapp.domain.model.Note
import com.meisterlabs.testapp.data.local.NoteDataSource
import com.meisterlabs.noteapp.domain.time.DateTimeUtil
import com.meisterlabs.testapp.domain.use_cases.SaveNoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class NoteDetailViewModel @Inject constructor(
    private val saveNoteUseCase: SaveNoteUseCase,
    private val noteDataSource: NoteDataSource,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    // you can do state object not single variable
    private val noteTitle = savedStateHandle.getStateFlow("noteTitle", "")
    private val isNoteTitleFocused = savedStateHandle.getStateFlow("isNoteTitleFocused", false)
    private val noteContent = savedStateHandle.getStateFlow("noteContent", "")
    private val isNoteContentFocused = savedStateHandle.getStateFlow("isNoteContentFocused", false)
    private val noteColor = savedStateHandle.getStateFlow("noteColor", Note.generateRandomColor())

    val state = combine(
        noteTitle,
        isNoteTitleFocused,
        noteContent,
        isNoteContentFocused,
        noteColor
    ) { title, isTitleFocused, content, isContentFocused, color ->
        NoteDetailState(
            noteTitle = title,
            isNoteTitleHintVisible = !isTitleFocused && title.isEmpty(),
            noteContent = content,
            isNoteContentHintVisible = !isContentFocused && content.isEmpty(),
            noteColor = color
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), NoteDetailState())

    private val _hasNoteBeenSaved = MutableStateFlow(false)
    val hasNoteBeenSaved = _hasNoteBeenSaved.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private var existingNodeId: String? = null

    init {
        // when it's edit
        savedStateHandle.get<String>("nodeId")?.let { existingNodeId ->
            if (existingNodeId.isBlank()) {
                // navigation doesn't support nullable
                return@let
            }
            this.existingNodeId = existingNodeId
            viewModelScope.launch {
                noteDataSource.getNoteById(existingNodeId)?.let { note ->
                    // save in saved state from data source
                    savedStateHandle["noteTitle"] = note.title
                    savedStateHandle["noteContent"] = note.content
                    savedStateHandle["noteColor"] = note.colorHex
                }
            }
        }
    }

    fun onNoteTitleChanged(text: String) {
        savedStateHandle["noteTitle"] = text
    }

    fun onNoteContentChanged(text: String) {
        savedStateHandle["noteContent"] = text
    }

    fun onNoteTitleFocusChanged(isFocused: Boolean) {
        savedStateHandle["isNoteTitleFocused"] = isFocused
    }

    fun onNoteContentFocusChanged(isFocused: Boolean) {
        savedStateHandle["isNoteContentFocused"] = isFocused
    }

    fun saveNote() {
        viewModelScope.launch {
            saveNoteUseCase.execute(
                Note(
                    id = existingNodeId,
                    title = noteTitle.value,
                    content = noteContent.value,
                    colorHex = noteColor.value,
                    created = DateTimeUtil.now()
                )
            )
            _hasNoteBeenSaved.value = true
        }
    }
}

