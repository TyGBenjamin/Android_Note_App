package com.rave.noteapp.presentation.adapters


import android.app.Activity
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.rave.noteapp.databinding.NotesBinding
import com.rave.noteapp.domain.models.Note
import kotlin.reflect.KFunction1

class MainAdapter(
    val nav: (id: Int) -> Unit,
    val deleteNote: (note:Note) -> Unit
        ): RecyclerView.Adapter<MainAdapter.NotesViewHolder>(){

    private lateinit var notesList: List<Note>


    inner class NotesViewHolder(
        private val binding: NotesBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun apply(note: Note) = with(binding) {
            tvTitle.text = note.title
            textView3.text = note.body

            btnDelete.setOnClickListener {

                deleteNote(note)
            }
            root.setOnClickListener{
                Log.d("CLICK", "Note: ${note.id}")
                nav(note.id)
            }


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val binding = NotesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NotesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int)   {
        val currentItem = notesList[position]
        holder.apply(currentItem)
    }

    override fun getItemCount(): Int {
        return this.notesList.size
    }

    fun addItems(note: List<Note>) {
        notesList = note
    }

}