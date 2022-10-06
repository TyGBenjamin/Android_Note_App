package com.rave.noteapp.presentation.fragments


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavArgs
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.rave.noteapp.databinding.FragmentViewNoteBinding
import com.rave.noteapp.databinding.NotesBinding
import com.rave.noteapp.domain.models.Note
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ViewNoteFragment (
): Fragment() {
    private  var _binding : FragmentViewNoteBinding? = null
    private val binding: FragmentViewNoteBinding get () = _binding!!
    private val viewModel by viewModels<ViewNoteViewModel>()
    private val safeArgs by navArgs<ViewNoteFragmentArgs>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentViewNoteBinding.inflate(inflater, container, false).also {
        _binding = it
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getNote(safeArgs.id)
        initViews()

    }
    private fun initViews() = with(binding) {
        lifecycleScope.launch {
            viewModel.note.collectLatest { note ->
                note?.let {
                     tvDetailTitle.setText(note.title)
                    tvBody.setText(note.body)
                }
                btnSave.setOnClickListener{
                    Log.d("CLICKADD", "${tvBody.text} ${tvDetailTitle.text} ")
                    if (note != null) {
                        viewModel.update(note = Note(id =note.id,title = tvDetailTitle.text.toString(), body = tvBody.text.toString()))
                    }
                    navigateToHome()
                }
            }
        }

    }





    private fun navigateToHome() {
        val action = ViewNoteFragmentDirections.actionViewNoteFragmentToDashboard()
        findNavController().navigate(action)
    }



//
//

}