package com.tmuniz570.stantcineminha.favotite.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.tmuniz570.stantcineminha.favotite.model.FilmesEntity

@Dao
interface FilmesDao {

    @Query("SELECT * FROM filmesentity")
    fun getAll(): List<FilmesEntity>

    @Insert
    fun insert(filmesEntity: FilmesEntity)

    @Delete
    fun delete(filmesEntity: FilmesEntity)

}