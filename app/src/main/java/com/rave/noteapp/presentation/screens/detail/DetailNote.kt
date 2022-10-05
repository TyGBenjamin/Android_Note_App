package com.rave.noteapp.presentation.screens.detail

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.rave.noteapp.R
import com.rave.noteapp.databinding.FragmentDetailNoteBinding
import com.rave.noteapp.databinding.FragmentHomeBinding
import com.rave.noteapp.presentation.screens.home.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailNote : Fragment() {
    private var _binding: FragmentDetailNoteBinding? = null
    private val binding: FragmentDetailNoteBinding get() = _binding!!
    private val viewModel by viewModels<DetailNoteViewModel>()
    private val safeArgs: DetailNoteArgs by navArgs()
//    private lateinit var viewModel: DetailNoteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentDetailNoteBinding.inflate(inflater, container, false).also {
        _binding = it
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() = with(binding) {
        lifecycleScope.launch {
            viewModel.setNote()
            viewModel.note.collectLatest { note ->
                println("initViews - Success $note")
                tvTitle.text = note?.title
                tvBody.text = note?.body

            }
        }
    }

    companion object {
        const val TAG = "FragmentLogger"
    }
}


