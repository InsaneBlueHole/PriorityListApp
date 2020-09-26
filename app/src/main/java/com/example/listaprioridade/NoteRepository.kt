package com.example.listaprioridade

import android.app.Application
import androidx.lifecycle.LiveData

class NoteRepository constructor(private val noteDao:NoteDao ) {


    val allNotes:LiveData<List<Note>> = noteDao.getAllNotes()


   fun insert(note:Note){
        noteDao.insert(note)
    }

    fun update(note:Note){
        noteDao.update(note)
    }

    fun delete(note:Note){
        noteDao.delete(note)
    }

    fun deleteAllNotes(){
        noteDao.deleteAllNotes()
    }



}