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
        private var getNotesUseCase: GetNotesUseCase? = nil
        private var noteDataSource: NoteDataSource? = nil
        private var deleteNodeUseCase: DeleteNoteUseCase? = nil
        
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
        
        
        init(noteDataSource: NoteDataSource? = nil, getNotesUseCase: GetNotesUseCase? = nil, deleteNoteUseCase: DeleteNoteUseCase? = nil) {
            self.noteDataSource = noteDataSource
            self.getNotesUseCase = getNotesUseCase
            self.deleteNodeUseCase = deleteNoteUseCase
        }
        
//        private var subscription: AnyCancellable?
        
        
        
        func loadNotes() {
//            Task {
//                  do {
//                    let stream = asyncStream(
//                        for: getNotesUseCase?.execute()
//                    )
//                    for try await data in stream {
//                        self.notes = notes ?? []
//                        self.filterNotes = self.notes
//                    }
//                  } catch {
//                    print("Failed with error: \(error)")
//                  }
//                }
            
//            subscription = FlowPublisher<NSArray>(flow: getNotesUseCase?.execute())
//                .map({ entities in
//                    entities.map { self.visualFactory.from(note: $0 as! PreviewNoteEntity) }
//                })
//                .assign(to: \.notes, on: self)
            
            noteDataSource?.getAllNotes(completionHandler: { notes, error in
                self.notes = notes ?? []
                self.filterNotes = self.notes

            })
        }
        
        func deleteNoteById(id: String) {
//            guard id != nil
//            deleteNodeUseCase?.execute(noteId: id, completionHandler: { resource, error in ->
//
//            })
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
        
        func setGetNotesUseCase(getNotesUseCase: GetNotesUseCase) {
            self.getNotesUseCase = getNotesUseCase
        }
        
        func setDeleteNoteUseCase(deleteNoteUseCase: DeleteNoteUseCase) {
            self.deleteNodeUseCase = deleteNoteUseCase
        }
        
    }
}
