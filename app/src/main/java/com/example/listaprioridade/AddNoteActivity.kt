package com.example.listaprioridade

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.EditText
import android.widget.NumberPicker
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_add_note.*
import kotlinx.android.synthetic.main.note_item.*

class AddNoteActivity : AppCompatActivity() {

    companion object{
        const val EXTRA_TITLE = "com.example.listaprioridade.EXTRA_TITLE"
        const val EXTRA_DESCRIPTION = "com.example.listaprioridade.EXTRA_DESCRIPTION"
        const val EXTRA_PRIORITY = "com.example.listaprioridade.EXTRA_PRIORITY"
    }


    lateinit var editTextTitle: TextView
    lateinit var editTextDescription: TextView
    lateinit var numberPicker: NumberPicker



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)
        editTextTitle = findViewById(R.id.edit_text_title)
        editTextDescription = findViewById(R.id.edit_text_description)
        numberPicker = number_pick_priority

        numberPicker.minValue = 1
        numberPicker.maxValue = 10


        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_baseline_close_24)
        setTitle("Add Note")

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater:MenuInflater = getMenuInflater()
        menuInflater.inflate(R.menu.add_note_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
       if(item.itemId == R.id.save_note) {
           saveNote()
           return true
       }

       else return super.onOptionsItemSelected(item)
    }

    private fun saveNote(){
        val title:String = editTextTitle?.text.toString()
        val description:String = editTextDescription?.text.toString()
        val priority:Int = numberPicker.value

        if(title.trim().isEmpty()|| description.trim().isEmpty()){
            Toast.makeText(this, "Please Insert a title and description",Toast.LENGTH_LONG).show()
            return
        }

        var data = Intent()
        data.putExtra(EXTRA_TITLE, title)
        data.putExtra(EXTRA_DESCRIPTION, description)
        data.putExtra(EXTRA_PRIORITY, priority)

        setResult(RESULT_OK, data)
        finish()
    }



}