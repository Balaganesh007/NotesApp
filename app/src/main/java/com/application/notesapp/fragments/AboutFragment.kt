package com.application.notesapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.application.notesapp.R
import com.application.notesapp.databinding.FragmentAboutBinding
import com.application.notesapp.databinding.FragmentDisplayAndAddBinding


class AboutFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding: FragmentAboutBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_about, container, false)

        return binding.root
    }
}