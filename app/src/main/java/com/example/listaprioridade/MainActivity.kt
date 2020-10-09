package com.example.listaprioridade

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    lateinit var  noteViewModel:NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button_add.setOnClickListener {
            val intent = Intent(this@MainActivity, AddNoteActivity::class.java)
            startActivityForResult(intent, 1)
        }


        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view_notes)
        val adapter = NoteAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)



        noteViewModel = ViewModelProvider(this).get(NoteViewModel::class.java)

        noteViewModel.allNotes.observe(this, Observer { notes ->
            notes?.let { adapter.setNotes(it) }
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1 && resultCode == RESULT_OK){

            val title: String = data?.getStringExtra(AddNoteActivity.EXTRA_TITLE).toString()
            val description =  data?.getStringExtra(AddNoteActivity.EXTRA_DESCRIPTION).toString()
            val priority = data!!.getIntExtra(AddNoteActivity.EXTRA_PRIORITY,1)

            var note = Note(title,description,priority)
            noteViewModel.insert(note)

            Toast.makeText(this, "Note Saved", Toast.LENGTH_LONG).show()
        }

        else{
            Toast.makeText(this, "Note not Saved", Toast.LENGTH_LONG).show()
        }
    }
}