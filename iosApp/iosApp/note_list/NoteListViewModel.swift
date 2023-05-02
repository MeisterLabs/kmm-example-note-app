//
//  NoteListViewModel.swift
//  iosApp
//
//  Created by Petra Cendelinova on 27.04.23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared

extension NoteListScreen {
    // observable object instead of ViewModel
    // mainActor - happening on main thread
    @MainActor class NoteListViewModel: ObservableObject {
        private var noteDataSource: NoteDataSource? = nil
        
        private let searchNotes = SearchNotesUseCase()
        
        private var notes = [Note]()
        // private settter
        @Published private(set) var filterNotes = [Note]()
        @Published var searchText = "" {
            didSet {
                // when seach text has changed
                self.filterNotes = searchNotes.execute(notes: self.notes, query: searchText)
            }
        }
        @Published private(set) var isSearchActive = false
        
        
        init(noteDataSource: NoteDataSource? = nil) {
            self.noteDataSource = noteDataSource
        }
        
        func loadNotes() {
            noteDataSource?.getAllNotes(completionHandler: { notes, error in
                self.notes = notes ?? []
                self.filterNotes = self.notes
                
            })
        }
        
        // equalient to Long
        func deleteNoteById(id: Int64) {
            if id != nil {
                noteDataSource?.deleteNoteByd(id: id, completionHandler: { error in
                    self.loadNotes()
                })
            }
        }
        
        func toggleIsSearchActive() {
            self.isSearchActive = !self.isSearchActive
            if !self.isSearchActive {
                searchText = ""
            }
        }
            
        func setNoteDataSource(noteDataSource: NoteDataSource) {
            self.noteDataSource = noteDataSource
        }
        
    }
}
