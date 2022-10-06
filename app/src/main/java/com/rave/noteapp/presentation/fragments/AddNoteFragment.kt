package com.rave.noteapp.presentation.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.rave.noteapp.R
import com.rave.noteapp.databinding.FragmentAddNoteBinding
import com.rave.noteapp.domain.models.Note
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddNoteFragment : Fragment() {
    private lateinit var _binding: FragmentAddNoteBinding
    private val binding: FragmentAddNoteBinding get() = _binding!!
    private val  viewModel by viewModels<AddNoteViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View= FragmentAddNoteBinding.inflate(inflater,container,false).also {
        _binding = it
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners()
    }


    private fun initListeners() = with(binding) {
            btnSave.setOnClickListener{
                Log.d("CLICKADD", "${etAddBody.text} ${etAddTitle.text} ")
                    viewModel.addNote(note = Note(title = etAddTitle.text.toString(), body = etAddBody.text.toString()))
                    navigateToHome()
        }
    }

    private fun navigateToHome() {
        val action = AddNoteFragmentDirections.actionAddNoteFragmentToDashboard()
        findNavController().navigate(action)
    }





}