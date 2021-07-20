package com.application.notesapp.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.application.notesapp.database.NotesDao

class DisplayViewModelFactory (
    private val dataSource: NotesDao,
    private val application: Application
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(DisplayViewModel::class.java)) {
                return DisplayViewModel(dataSource,application) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
