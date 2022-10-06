package com.rave.noteapp.presentation.screens.home

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.rave.noteapp.adapters.NoteAdapter
import com.rave.noteapp.databinding.FragmentHomeBinding
import com.rave.noteapp.domain.models.Note
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import com.rave.noteapp.R


@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding get() = _binding!!
    private val viewModel by viewModels<HomeViewModel>()
//    private val noteAdapter by lazy { NoteAdapter(::navigateToDetails, ::deleteNote) }
    lateinit var searchView: SearchView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentHomeBinding.inflate(inflater, container, false).also {
        _binding = it
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpMenu()

        initViews()
        initListeners()

//        viewModel.insertNote()
    }

    private fun setUpMenu() {
        (requireActivity() as MenuHost).addMenuProvider(object : MenuProvider {
            override fun onPrepareMenu(menu: Menu) {
                // Handle for example visibility of menu items
            }

            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                // Add menu items here
                menuInflater.inflate(R.menu.dropdown_menu, menu)
                val searchItem = menu.findItem(R.id.btn_search)
                searchView = searchItem.actionView as SearchView
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                // Handle the menu selection
                when (menuItem.itemId) {
                    R.id.btn_search -> {
                        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                            override fun onQueryTextSubmit(query: String?): Boolean {
                                Log.d("Logger", "onQueryTextSubmit: $query")
                                if (query != null) viewModel.updateSearchText(query)
                                return true
                            }

                            override fun onQueryTextChange(newText: String?): Boolean {
                                return true
                            }
                        })
                    }
                }
                return true
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
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
                rvView.adapter = NoteAdapter(::navigateToDetails, ::deleteNote).apply {
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
        viewModel.deleteNote(note)
        val test = object :Test {
            override fun doSomeShit() {

            }
        }
        anotherTest(test)
    }

    fun anotherTest(something: Test) {
        something.doSomeShit()
    }

//    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
//        inflater.inflate(R.menu.dropdown_menu, menu)
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        return super.onOptionsItemSelected(item)
//    }
}

interface Test {
    fun doSomeShit()
}




