//
//  NoteDetailScreen.swift
//  iosApp
//
//  Created by Petra Cendelinova on 27.04.23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared

struct NoteDetailScreen: View {
    private var noteDataSource: NoteDataSource
    private var noteId: String? = nil
    
    @StateObject var viewModel = NoteDetailViewModel(noteDataSource: nil)
    
    // backstack
    @Environment(\.presentationMode) var presentation
    
    init(noteDataSource: NoteDataSource, noteId: String? = nil) {
        self.noteDataSource = noteDataSource
        self.noteId = noteId
    }
    
    var body: some View {
        VStack(alignment: .leading) {
            TextField("Enter a title", text: $viewModel.noteTitle)
                .font(.title)
            TextField("Enter some content...",  text: $viewModel.noteContent)
            // occupy all space
            Spacer()
        }.toolbar(content: {
            Button(action: {
                viewModel.saveNote {
                    // pop up backstack
                    self.presentation.wrappedValue.dismiss()
                }
            }) {
                Image(systemName: "checkmark")
            }
        })
        .padding()  // default padding
        .background(Color(hex: viewModel.noteColor))
        .onAppear {
            viewModel.setParamsAndLoadNote(noteDataSource: noteDataSource, noteId: noteId)
        }
    }
}

struct NoteDetailScreen_Previews: PreviewProvider {
    static var previews: some View {
        EmptyView()
    }
}
