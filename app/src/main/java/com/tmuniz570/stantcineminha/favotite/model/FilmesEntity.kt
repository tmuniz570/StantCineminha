package com.tmuniz570.stantcineminha.favotite.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class FilmesEntity(
    @PrimaryKey(autoGenerate = false) var id: Int,
    var original_language: String,
    var overview:String,
    var poster_path:String,
    var release_date: String,
    var title: String,
    var generos: String
) : Parcelable