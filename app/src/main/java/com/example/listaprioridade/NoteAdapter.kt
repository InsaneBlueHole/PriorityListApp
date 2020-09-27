package com.example.listaprioridade

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.ArrayList

class NoteAdapter internal constructor(context: Context): RecyclerView.Adapter<NoteAdapter.NoteViewHolder>(){

    private var notes = listOf<Note>()

    inner class NoteViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        var textView_Title:TextView = itemView.findViewById(R.id.text_title)
        var textView_Description:TextView = itemView.findViewById(R.id.text_description)
        var textView_Priority:TextView = itemView.findViewById(R.id.text_priority)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.note_item, parent,false)
        return NoteViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val currentNote = notes[position]
        holder.textView_Title.text = currentNote.title
        holder.textView_Description.text = currentNote.description
        holder.textView_Priority.text = currentNote.priority.toString()
    }

    internal fun setNotes(notes:List<Note>){
        this.notes = notes
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return notes.size
    }

}