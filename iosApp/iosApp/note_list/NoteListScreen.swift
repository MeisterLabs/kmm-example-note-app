//
//  NoteListScreen.swift
//  iosApp
//
//  Created by Petra Cendelinova on 27.04.23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared

struct NoteListScreen: View {
    private var noteDataSource: NoteDataSource
    private var getNoteUseCase: GetNotesUseCase
    @StateObject var viewModel = NoteListViewModel(noteDataSource: nil, getNotesUseCase: nil)
    
    @State private var isNoteSelected = false
    @State private var selectedNoteId: String? = nil
    
    init(noteDataSource: NoteDataSource, getNotesUseCase: GetNotesUseCase) {
        self.noteDataSource = noteDataSource
        self.getNoteUseCase = getNotesUseCase
    }
    
    var body: some View {
        VStack {
            // compose Box
            ZStack {
                NavigationLink(destination: NoteDetailScreen(noteDataSource: self.noteDataSource, noteId: selectedNoteId), isActive: $isNoteSelected) {
                    EmptyView()
                }.hidden()
                
                HideableSearchTextField<NoteDetailScreen>(onSearchToggled: {
                    viewModel.toggleIsSearchActive()
                }, destinationProvider: {
                    NoteDetailScreen(
                    noteDataSource: noteDataSource,
                    noteId: selectedNoteId
                    )
                }, isSearchActive: viewModel.isSearchActive,
                  searchText: $viewModel.searchText
                )
                .frame(maxWidth: .infinity, minHeight: 40)
                .padding()
                
                if !viewModel.isSearchActive {
                    Text("all_notes")
                        .font(.title2)
                }
            }
            
            List {
                // getting id of the item
                ForEach(viewModel.filterNotes, id: \.self.id) { note in
                    Button(action: {
                        isNoteSelected = true
                        selectedNoteId = note.id!
                    }) {
                        NoteItem(note: note, onDeleteClick: {
                            viewModel.deleteNoteById(id: note.id!)
                        })
                    }
                    
                }
            }.onAppear {
                // load on appeared
                viewModel.loadNotes()
            }
            .listStyle(.plain)
            .listRowSeparator(.hidden)
        }
        .onAppear {
            viewModel.setNoteDataSource(noteDataSource: noteDataSource)
            viewModel.setGetNoteUseCase(getNoteUseCase: getNoteUseCase)
        }
    }
}

struct NoteListScreen_Previews: PreviewProvider {
    static var previews: some View {
      EmptyView()
    }
}
