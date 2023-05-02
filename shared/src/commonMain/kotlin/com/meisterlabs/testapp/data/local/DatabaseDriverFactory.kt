package com.meisterlabs.testapp.data.local

import com.squareup.sqldelight.db.SqlDriver

// there must be actual exp
expect class DatabaseDriverFactory {

    fun createDriver(): SqlDriver
}