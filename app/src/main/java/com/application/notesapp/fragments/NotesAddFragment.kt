package com.application.notesapp.fragments

import android.graphics.Path
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.application.notesapp.R
import com.application.notesapp.database.NoteDatabase
import com.application.notesapp.databinding.FragmentNotesAddBinding
import com.application.notesapp.viewmodel.NotesViewModel
import com.application.notesapp.viewmodel.NotesViewModelFactory


class NotesAddFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding: FragmentNotesAddBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_notes_add, container, false)

        val application = requireNotNull(this.activity).application

        val dataSource = NoteDatabase.getInstance(application).notesDao

        val viewModelFactory = NotesViewModelFactory(dataSource, application)

        val notesViewModel =
            ViewModelProvider(
                this, viewModelFactory).get(NotesViewModel::class.java)

        binding.notesViewModel = notesViewModel

        binding.setLifecycleOwner(this)

        notesViewModel.navigateToDisplayFragment.observe(viewLifecycleOwner,Observer{
            if(it == true) {
                this.findNavController().navigate(R.id.action_notesaddFragment_to_displayFragment)
                notesViewModel.doneNavigation()
            }
        })

        return binding.root
    }

}
