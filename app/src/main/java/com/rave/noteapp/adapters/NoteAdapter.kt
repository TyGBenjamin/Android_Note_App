package com.rave.noteapp.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rave.noteapp.data.local.NoteDao
import com.rave.noteapp.databinding.ItemListBinding
import com.rave.noteapp.domain.models.Note

class NoteAdapter (
    val navigate: (id: Int) -> Unit,
    val deleteNote: (note: Note) -> Unit
) : RecyclerView.Adapter<NoteAdapter.NoteListViewHolder>() {
    private lateinit var noteList: List<Note>

    inner class NoteListViewHolder(
        private val binding: ItemListBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun applyNote(note: Note) = with(binding) {
            tvTitle.text = note.title
            tvBody.text = note.body

            root.setOnClickListener{
                Log.d("CLICK", "applyNote: ${note.id}")
                navigate(note.id)
            }
            btnDelete.setOnClickListener {
                println("Delete btn clicked")
                deleteNote(note)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteListViewHolder {
        val binding = ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoteListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NoteListViewHolder, position: Int) {
        val item = noteList[position]
        holder.applyNote(item)
    }

    override fun getItemCount() = noteList.size

    fun addItems(noteList: List<Note>) {
        this.noteList = noteList
    }

}