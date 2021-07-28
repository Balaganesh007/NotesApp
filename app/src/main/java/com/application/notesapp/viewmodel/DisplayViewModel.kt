package com.application.notesapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.application.notesapp.database.Notes
import com.application.notesapp.database.NotesDao
import kotlinx.coroutines.*

class DisplayViewModel(
    val dataSource: NotesDao,
application: Application
) : AndroidViewModel(application){


    private var viewModelJob = Job()

    override fun onCleared(){
        super.onCleared()
        viewModelJob.cancel()
    }
    private val scope = CoroutineScope(Dispatchers.Main+viewModelJob)
    private val notes = MutableLiveData<Notes?>()

    var allNotes = dataSource.getAllNotes()

    fun onClear(){
        scope.launch {
            clear()
            notes.value = null
        }
    }
    private suspend fun clear() {
        withContext(Dispatchers.IO) {
            dataSource.deleteAllNotes()
        }
    }
    fun onDeleteButton(){
        scope.launch {
            onClear()
        }
    }

}