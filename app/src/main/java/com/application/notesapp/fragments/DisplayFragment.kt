package com.application.notesapp.fragments

import android.os.Bundle
import android.util.Log
import android.view.*
import android.view.WindowInsets.Side.all
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.application.notesapp.R
import com.application.notesapp.database.NoteDatabase
import com.application.notesapp.database.NotesDao
import com.application.notesapp.databinding.FragmentDisplayAndAddBinding
import com.application.notesapp.viewmodel.DisplayViewModel
import com.application.notesapp.viewmodel.DisplayViewModelFactory
import com.application.notesapp.viewmodel.NotesViewModel
import com.application.notesapp.viewmodel.NotesViewModelFactory

class DisplayFragment : Fragment() {
    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding: FragmentDisplayAndAddBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_display_and_add, container, false)

        binding.addfloatingActionButton.setOnClickListener { view ->
            view.findNavController().navigate(R.id.action_displayFragment_to_notesaddFragment)
        }

        setHasOptionsMenu(true)

        val application = requireNotNull(this.activity).application

        val dataSource = NoteDatabase.getInstance(application).notesDao

        val viewModelFactory = DisplayViewModelFactory(dataSource, application)

        val displayViewModel =
            ViewModelProvider(
                this, viewModelFactory).get(DisplayViewModel::class.java)

        binding.displayViewModel = displayViewModel


        displayViewModel.allNotes.observe(viewLifecycleOwner, Observer {
            binding.DisplaytextView.setText(it.get(0).title)
        })

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.overflow_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item!!,
            requireView().findNavController())
                || super.onOptionsItemSelected(item)
    }


}