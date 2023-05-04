import SwiftUI
import shared

@main
struct iOSApp: App {
    private let diModule = DiModule()
    
	var body: some Scene {
		WindowGroup {
            NavigationView {
                NoteListScreen(noteDataSource: diModule.noteDataSource, getNotesUseCase: diModule.getNotesUseCase)
            }.accentColor(.black)
        }
	}
}
