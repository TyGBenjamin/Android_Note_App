package com.rave.noteapp.presentation.screens.add

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rave.noteapp.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddNote : Fragment() {

    companion object {
        fun newInstance() = AddNote()
    }

    private lateinit var viewModel: AddNoteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_note, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AddNoteViewModel::class.java)
        // TODO: Use the ViewModel
    }

}