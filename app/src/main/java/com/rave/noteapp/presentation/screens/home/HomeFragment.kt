package com.rave.noteapp.presentation.screens.home

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.rave.noteapp.R
import com.rave.noteapp.adapters.NoteAdapter
import com.rave.noteapp.data.local.NoteDao
import com.rave.noteapp.databinding.FragmentHomeBinding
import com.rave.noteapp.databinding.ItemListBinding
import com.rave.noteapp.domain.models.Note
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding get() = _binding!!
    private val viewModel by viewModels<HomeViewModel>()
    private val noteAdapter by lazy { NoteAdapter(::navigateToDetails, ::deleteNote) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentHomeBinding.inflate(inflater, container, false).also {
        _binding = it
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initListeners()
        viewModel.insertNote()
    }

    private fun initListeners() = with(binding) {
        addButton.setOnClickListener {
            println("addButton clicked")
            navigateToNewNote()
        }
    }

    private fun initViews() = with(binding) {
        lifecycleScope.launch(Dispatchers.Main) {
            viewModel.noteList.collectLatest { noteList ->
                rvView.adapter = noteAdapter.apply {
                    addItems(noteList)
                }
            }
        }

    }

    private fun navigateToDetails(Id: Int) {
        val action = HomeFragmentDirections.actionHomeFragmentToDetailNote(Id)
        findNavController().navigate(action)
    }

    private fun navigateToNewNote() {
        val action = HomeFragmentDirections.actionHomeFragmentToAddNote()
        findNavController().navigate(action)
    }

    private fun deleteNote(note: Note) {
        // viewmodel.deleteNote(id)
    }
}

