package com.tmuniz570.stantcineminha.favotite.model

import android.graphics.ImageFormat
import android.media.Image
import android.os.Parcelable
import android.widget.ImageView
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.io.File

@Entity
@Parcelize
data class FilmesEntity(
    @PrimaryKey var id: Int,
    var original_language: String,
    var overview: String,
    var poster_path: String,
    var release_date: String,
    var title: String,
    var generos: String
) : Parcelable