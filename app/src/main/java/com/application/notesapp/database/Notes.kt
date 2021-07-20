package com.application.notesapp.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(
    tableName = "notes_database"
)
class Notes(
        @ColumnInfo(name = "title")
        var title: String = "",

        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "id")
        var id: Int = 0,

        @ColumnInfo(name = "description")
        var description: String = "",
)
