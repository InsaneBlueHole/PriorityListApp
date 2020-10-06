package com.example.listaprioridade

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.NumberPicker

class AddNoteActivity : AppCompatActivity() {

    lateinit var editTextTitle: EditText
    lateinit var editTextDescription: EditText
    lateinit var numberPicker:NumberPicker

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)

        editTextTitle = findViewById(R.id.text_title)
        editTextDescription = findViewById(R.id.text_description)
        numberPicker = findViewById(R.id.number_pick_priority)

        numberPicker.minValue = 1
        numberPicker.maxValue = 10


    }
}