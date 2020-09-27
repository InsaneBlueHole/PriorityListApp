package com.example.listaprioridade

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [Note::class], version = 1,exportSchema = false)
abstract class NoteDatabase: RoomDatabase() {

    abstract fun noteDao(): NoteDao

    class NoteDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {
        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let { database ->
                scope.launch {
                    val noteDao = database.noteDao()

                    noteDao.deleteAllNotes()


                    var note = Note("title 2", "description 5", 3)
                    noteDao.insert(note)
                    note = Note("title 2", "description 5", 2)
                    noteDao.insert(note)
                    note = Note("title 2", "description 5", 3)
                    noteDao.insert(note)
                    note = Note("title 2", "description 5", 1)
                    noteDao.insert(note)

                }
            }
        }
    }


    companion object {
        @Volatile
        private var INSTANCE: NoteDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): NoteDatabase {

            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NoteDatabase::class.java,
                    "note_database"
                )
                    .addCallback(NoteDatabaseCallback(scope))
                    .build()

                INSTANCE = instance

                instance
            }
        }

    }
}