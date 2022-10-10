package com.rave.noteapp.presentation.screens.edit

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.rave.noteapp.R
import com.rave.noteapp.databinding.FragmentDetailNoteBinding
import com.rave.noteapp.databinding.FragmentEditNoteBinding
import com.rave.noteapp.domain.models.Note
import com.rave.noteapp.presentation.screens.add.AddNoteDirections
import com.rave.noteapp.presentation.screens.detail.DetailNoteArgs
import com.rave.noteapp.presentation.screens.detail.DetailNoteViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class EditNote : Fragment() {
    private var _binding: FragmentEditNoteBinding? = null
    private val binding: FragmentEditNoteBinding get() = _binding!!
    private val viewModel by viewModels<EditNoteViewModel>()
    private val safeArgs: EditNoteArgs by navArgs()


//    private lateinit var viewModel: EditNoteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentEditNoteBinding.inflate(inflater, container, false).also {
        _binding = it
    }.root


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getNote(id)
        initViews()
    }

    private fun navigateToHome() {
        val action = EditNoteDirections.actionEditNoteToHomeFragment()
        findNavController().navigate(action)
    }

    private fun initViews() = with(binding) {
        lifecycleScope.launch {
            viewModel.note.collectLatest { note ->
                note?.let {
                    println("initViews - Success $note")
                    Log.d("EDIT", note.title)
                    editTitle.setText(note.title)
                    editBody.setText(note.body)
                }
                btnSave.setOnClickListener {
                    println("saveButton clicked")
                    viewModel.update(
                        note = Note(
                            title = editTitle.text.toString(),
                            body = editBody.text.toString()
                        )
                    )
                    navigateToHome()
                }
            }


        }

    }

}
