package com.meisterlabs.testapp.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.meisterlabs.testapp.android.note_detail.NoteDetailScreen
import com.meisterlabs.testapp.android.note_list.NoteListScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = Screen.NoteListScreen.route
                ) {
                    composable(route = Screen.NoteListScreen.route) {
                        NoteListScreen(navController = navController)
                    }
                    composable(
                        route = "${Screen.NoteDetailScreen.route}/{noteId}",
                        arguments = listOf(
                            navArgument(name = "noteId") {
                                type = NavType.StringType
                                defaultValue = ""
                            }
                        )
                    ) {
                        NoteDetailScreen(navController = navController)
                    }
                }
            }
        }
    }
}