package com.meisterlabs.testapp.domain.model

import com.meisterlabs.testapp.presentation.BabyBlueHex
import com.meisterlabs.testapp.presentation.LightGreenHex
import com.meisterlabs.testapp.presentation.RedOrangeHex
import com.meisterlabs.testapp.presentation.RedPinkHex
import com.meisterlabs.testapp.presentation.VioletHex
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
