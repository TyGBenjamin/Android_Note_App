package com.rave.noteapp.presentation.screens.detail

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import com.rave.noteapp.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailNote : Fragment() {

    companion object {
        fun newInstance() = DetailNote()
    }

    private lateinit var viewModel: DetailNoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail_note, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(DetailNoteViewModel::class.java)
        // TODO: Use the ViewModel
    }


}