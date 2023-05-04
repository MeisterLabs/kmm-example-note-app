package com.meisterlabs.testapp.android.note_list

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.meisterlabs.kmmtasknote.common.Resource
import com.meisterlabs.testapp.domain.model.Note
import com.meisterlabs.testapp.domain.use_cases.DeleteNoteUseCase
import com.meisterlabs.testapp.domain.use_cases.GetNotesUseCase
import com.meisterlabs.testapp.domain.use_cases.SearchNotesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

@HiltViewModel
class NoteListViewModel @Inject constructor(
    private val getNotesUseCase: GetNotesUseCase,
    private val deleteNoteUseCase: DeleteNoteUseCase,
    private val searchNotesUseCase: SearchNotesUseCase,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val notes = savedStateHandle.getStateFlow("notes", emptyList<Note>())
    private val searchText = savedStateHandle.getStateFlow("searchText", "")
    private val isSearchActive = savedStateHandle.getStateFlow("isSearchActive", false)
    private val isLoading = savedStateHandle.getStateFlow("isLoading", false)
    private val error = savedStateHandle.getStateFlow("error", "")

    val state = combine(
        notes,
        searchText,
        isSearchActive,
        isLoading,
        error
    ) { notes, searchText, isSearchActive, isLoading, error ->
        NoteListState(
            notes = searchNotesUseCase.execute(notes, searchText),
            searchText = searchText,
            isSearchActive = isSearchActive,
            isLoading = isLoading,
            error = error
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), NoteListState())

    fun loadNotes() {
        viewModelScope.launch {
            getNotesUseCase.execute().collect { resource ->
                savedStateHandle["isLoading"] = false
                when (resource) {
                    is Resource.Error -> {
                        savedStateHandle["error"] = resource.message
                    }
                    is Resource.Loading -> {
                        savedStateHandle["isLoading"] = true
                    }
                    is Resource.Success -> {
                        savedStateHandle["notes"] = resource.data
                    }
                }
            }
        }
    }

    fun onSearchTextChange(text: String) {
        savedStateHandle["searchText"] = text
    }

    fun onToggleSearch() {
        savedStateHandle["isSearchActive"] = !isSearchActive.value
        if (!isSearchActive.value) {
            savedStateHandle["searchText"] = ""
        }
    }

    fun deleteNoteById(id: String) {
        viewModelScope.launch {
            deleteNoteUseCase.execute(id)
            // we should use flow but ios has problem with it
            loadNotes()
        }
    }
}