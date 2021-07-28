package com.application.notesapp.adapter

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.application.notesapp.database.Notes
@BindingAdapter("NotesTitle")
fun TextView.setNotesTitle(item : Notes?){
    item?.let {
        text = item.title.toString()
    }
}
@BindingAdapter("NotesDescription")
fun TextView.setNotesDescription(item : Notes?){
    item?.let {
        text = item.description.toString()
    }
}