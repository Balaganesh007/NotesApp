package com.application.notesapp.database
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface NotesDao {
    @Insert
    suspend fun insertNotes(notes: Notes)

    @Update
    suspend fun updateNotes(notes: Notes)

    @Query ("SELECT * FROM notes_database ORDER BY id DESC")
    fun getAllNotes(): LiveData<List<Notes>>

    @Query("SELECT * FROM notes_database ORDER BY id DESC LIMIT 1")
    fun getOneValue() : Notes?

    @Query("DELETE FROM notes_database")
    fun deleteAllNotes()
}