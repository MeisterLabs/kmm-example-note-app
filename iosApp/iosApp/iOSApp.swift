import SwiftUI
import shared

@main
struct iOSApp: App {
    private let databaseModule = DatabaseModule()
    private let diModule = DomainModule()
    
	var body: some Scene {
		WindowGroup {
            NavigationView {
                NoteListScreen(noteDataSource: databaseModule.noteDataSource, getNotesUseCase: databaseModule.getNotesUseCase)
            }.accentColor(.black)
        }
	}
}
