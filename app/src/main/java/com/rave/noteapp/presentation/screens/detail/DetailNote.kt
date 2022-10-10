package com.rave.noteapp.presentation.screens.detail

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.rave.noteapp.R
import com.rave.noteapp.adapters.NoteAdapter
import com.rave.noteapp.data.local.NoteDao
import com.rave.noteapp.databinding.FragmentDetailNoteBinding
import com.rave.noteapp.databinding.FragmentHomeBinding
import com.rave.noteapp.presentation.screens.home.HomeFragmentDirections
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
    lateinit var searchView: SearchView
//    private lateinit var viewModel: DetailNoteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentDetailNoteBinding.inflate(inflater, container, false).also {
        _binding = it
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       setupMenu()
        initViews()
//        initListeners()

//        viewModel.insertNote()
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

    private fun setupMenu(){
        val menuHost: MenuHost = requireActivity()


        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                // Add menu items here
                menuInflater.inflate(R.menu.edit_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {

                // Handle the menu selection
                when (menuItem.itemId) {
                    R.id.btn_edit -> {
                        Log.d("EditButton", "onMenuItemSelected: Edit Button Clicked")
                        println("Edit clicked")
                        navigateToEdit(id)
                        viewModel.setNote()
                        return true
                    }


                }


                return true
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    private fun navigateToEdit(Id:Int) {
        val action = DetailNoteDirections.actionDetailNoteToEditNote(Id)
        findNavController().navigate(action)
    }

    companion object {
        const val TAG = "FragmentLogger"
    }
}


