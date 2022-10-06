package com.rave.noteapp.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import com.rave.noteapp.databinding.FragmentDashboardBinding
import com.rave.noteapp.databinding.NotesBinding
import com.rave.noteapp.presentation.adapters.MainAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class Dashboard : Fragment() {
    private var _binding: FragmentDashboardBinding? = null
    private val binding: FragmentDashboardBinding get() = _binding!!
    private val viewModel by viewModels<DashboardViewModel>()
    private  val MainAdapter by lazy {MainAdapter  (::navigateToDetails)}


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ):
            View = FragmentDashboardBinding.inflate(inflater, container, false).also {
        _binding = it
    }.root


    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews()  = with(binding)  {
        lifecycleScope.launch {
            viewModel.note.collectLatest { note ->
                rvMain.adapter = MainAdapter.apply { addItems(note) }
            }
        }
    }
    private fun navigateToDetails(Id: Int) {
        val action = DashboardDirections.actionDashboardToViewNoteFragment(Id)
        findNavController().navigate(action)

    }

}

