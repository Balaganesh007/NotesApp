package com.application.notesapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.application.notesapp.database.NotesDao
import kotlinx.coroutines.Job

class DisplayViewModel(
    val dataSource: NotesDao,
application: Application
) : AndroidViewModel(application){

    private var viewModelJob = Job()

    override fun onCleared(){
        super.onCleared()
        viewModelJob.cancel()
    }
    //Hello TestCommit


    var allNotes = dataSource.getAllNotes()

}