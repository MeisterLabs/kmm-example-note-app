package com.meisterlabs.testapp.domain.model

import com.meisterlabs.noteapp.presentation.BabyBlueHex
import com.meisterlabs.noteapp.presentation.LightGreenHex
import com.meisterlabs.noteapp.presentation.RedOrangeHex
import com.meisterlabs.noteapp.presentation.RedPinkHex
import com.meisterlabs.noteapp.presentation.VioletHex
import kotlinx.datetime.LocalDateTime

data class Note(
    val id: String?,
    val title: String,
    val content: String,
    val colorHex: Long,
    val created: LocalDateTime,
) {
    companion object {
        private val colors = listOf(RedOrangeHex, RedPinkHex, LightGreenHex, BabyBlueHex, VioletHex)

        fun generateRandomColor() = colors.random()
    }
}
