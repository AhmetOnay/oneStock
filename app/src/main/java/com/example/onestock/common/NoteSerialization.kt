package com.example.onestock.common

import com.example.onestock.models.Note
import kotlinx.serialization.json.Json
import kotlinx.serialization.builtins.ListSerializer

object NoteSerialization {

    // Serialize a list of notes to a JSON string
    fun serializeNotes(notes: List<Note>): String {
        return Json.encodeToString(ListSerializer(Note.serializer()), notes)
    }

    // Deserialize a JSON string to a list of notes
    fun deserializeNotes(notesJson: String): List<Note> {
        return try {
            if (notesJson.isBlank() || !notesJson.trim().startsWith("[")) {
                emptyList()
            } else {
                Json.decodeFromString(ListSerializer(Note.serializer()), notesJson)
            }
        } catch (e: Exception) {
            emptyList()
        }
    }
}
