package com.tmuniz570.stantcineminha.model

data class NowPlaying(val results: List<Results>){
    inner class Results(
        val genre_ids: List<Int>,
        val id: Int,
        val poster_path:String,
        val release_date: String,
        val title: String
    )
}