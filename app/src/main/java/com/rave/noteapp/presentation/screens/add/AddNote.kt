package com.rave.noteapp.presentation.screens.add

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import com.rave.noteapp.R
import com.rave.noteapp.databinding.FragmentAddNoteBinding
import com.rave.noteapp.databinding.FragmentDetailNoteBinding
import com.rave.noteapp.presentation.screens.detail.DetailNoteViewModel
import com.rave.noteapp.presentation.screens.home.HomeFragmentDirections
import com.rave.noteapp.presentation.screens.home.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AddNote : Fragment() {
    private var _binding: FragmentAddNoteBinding? = null
    private val binding: FragmentAddNoteBinding get() = _binding!!
    private val viewModel by viewModels<AddNoteViewModel>()


//    private lateinit var viewModel: AddNoteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentAddNoteBinding.inflate(inflater, container, false).also {
        _binding = it
    }.root

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun initListeners() = with(binding) {
        btnSave.setOnClickListener {
            println("saveButton clicked")
            viewModel.addNote(title = tiTitle.text.toString(), body = tiBody.text.toString())
            navigateToHome()


        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initListeners()
    }

    private fun initViews() = with(binding) {
        lifecycleScope.launch(Dispatchers.Main) {

        }
    }

    private fun navigateToHome() {
        val action = AddNoteDirections.actionAddNoteToHomeFragment()
        findNavController().navigate(action)
    }
}

