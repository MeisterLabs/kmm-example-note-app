//
//  HideableSearchTextField.swift
//  iosApp
//
//  Created by Petra Cendelinova on 27.04.23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct HideableSearchTextField<Destination: View>: View {
    
    var onSearchToggled: () -> Void
    var destinationProvider: () -> Destination
    var isSearchActive: Bool
    // two-way binding
    @Binding var searchText: String
    
    var body: some View {
        // horizontal stack
        HStack {
            TextField("Search...", text: $searchText)
                .textFieldStyle(.roundedBorder)
                .opacity(isSearchActive ? 1: 0)
            if !isSearchActive {
                Spacer()
            }
            Button(action: onSearchToggled) {
                Image(systemName: isSearchActive ? "xmark" : "magnifyingglass")
            }
            NavigationLink(destination: destinationProvider()) {
                Image(systemName: "plus")
            }
        }
    }
}

struct HideableSearchTextField_Previews: PreviewProvider {
    static var previews: some View {
        HideableSearchTextField(
            onSearchToggled: { },
            destinationProvider: {EmptyView()},
            isSearchActive: true,
            searchText: .constant("Youtube")
        )
    }
}
