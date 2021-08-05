package com.tmuniz570.stantcineminha.model

data class Filmes(var results: List<Results>) {
    inner class Results(
        val genre_ids: List<Int>,
        val id: Int,
        val original_language: String,
        val overview:String,
        val poster_path:String,
        var release_date: String,
        val title: String,
        var favorito: Boolean,
        var generos: String
    )
}