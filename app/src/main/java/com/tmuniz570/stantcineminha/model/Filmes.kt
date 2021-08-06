package com.tmuniz570.stantcineminha.model

data class Filmes(var results: MutableList<Results>)

data class Results(
    val genre_ids: List<Int>,
    var id: Int,
    var original_language: String,
    var overview:String,
    var poster_path:String,
    var release_date: String,
    var title: String,
    var favorito: Boolean,
    var generos: String
)