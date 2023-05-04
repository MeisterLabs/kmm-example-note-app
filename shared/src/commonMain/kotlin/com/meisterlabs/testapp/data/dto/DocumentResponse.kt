package com.meisterlabs.testapp.data.dto

import kotlinx.serialization.Serializable

/**
 *
 * {
"name": "projects/kmm-meisterlabs-note-app/databases/(default)/documents/users/FKK5xUlVsrRNhGh8nr74SDU6RFr1/notes/KtH2IIPVoQ2yOr6SBmJd",
"fields": {
"name": {
"stringValue": "name 2"
},
"content": {
"stringValue": "content 2"
}
},
"createTime": "2023-05-04T09:30:29.081688Z",
"updateTime": "2023-05-04T09:30:29.081688Z"
},
 */
@Serializable
data class DocumentResponse(
    val name: String,
    val fields: NoteFields,
    val createTime: String,
    val updateTime: String
)
