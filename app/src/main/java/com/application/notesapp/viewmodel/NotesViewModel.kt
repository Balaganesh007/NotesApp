package com.application.notesapp.viewmodel



import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.application.notesapp.database.Notes
import com.application.notesapp.database.NotesDao
import kotlinx.coroutines.*


class NotesViewModel(
    val dataSource: NotesDao,
    application: Application
) : AndroidViewModel(application) {
    private var viewModelJob = Job()

    override fun onCleared(){
        super.onCleared()
        viewModelJob.cancel()
    }
    private val scope = CoroutineScope(Dispatchers.Main+viewModelJob)
    private val notes = MutableLiveData<Notes?>()
    private val allNotes = dataSource.getAllNotes()

    private val _navigateToDisplayFragment = MutableLiveData<Boolean>()
    val navigateToDisplayFragment : LiveData<Boolean>
        get() = _navigateToDisplayFragment
    fun doneNavigation(){
        _navigateToDisplayFragment.value = null
    }

    init {
        initializeTheNotes()
    }
    private fun initializeTheNotes(){
        scope.launch {
            notes.value = getNotesFromDatabase()
        }
    }
    private suspend fun getNotesFromDatabase():Notes?{
        return withContext(Dispatchers.IO){
            val note = dataSource.getOneValue()
            note
        }
    }
    private suspend fun insert(notes: Notes){
        withContext(Dispatchers.IO){
            dataSource.insertNotes(notes)
        }
    }
    private suspend fun update(notes: Notes){
        withContext(Dispatchers.IO){
            dataSource.updateNotes(notes)
        }
    }
    fun onSaveButton(description : String , title : String){
        scope.launch {
            insert(Notes(description = description,title = title))
            _navigateToDisplayFragment.value = true
            notes.value = getNotesFromDatabase()
        }
    }
}

