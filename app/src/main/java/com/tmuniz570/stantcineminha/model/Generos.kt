package com.tmuniz570.stantcineminha.model

data class Generos(var genres: List<Genres>){
    inner class Genres(
        val id: Int,
        val name: String
    )
}