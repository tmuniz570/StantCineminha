package com.tmuniz570.stantcineminha.favotite.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.tmuniz570.stantcineminha.favotite.dao.FilmesDao
import com.tmuniz570.stantcineminha.favotite.model.FilmesEntity

@Database(entities = [FilmesEntity::class], version = 1)
abstract class FilmesDatabase : RoomDatabase() {
    abstract fun filmesDao(): FilmesDao

    companion object {
        @Volatile
        private var INSTANCE: FilmesDatabase? = null

        fun getInstance(context: Context?): FilmesDatabase {
            synchronized(this) {
                var instance: FilmesDatabase? = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context!!,
                        FilmesDatabase::class.java,
                        "filmes_database"
                    ).allowMainThreadQueries().build()
                }
                return instance
            }
        }
    }
}