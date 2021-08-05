package com.tmuniz570.stantcineminha.model

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {
    @GET("3/movie/now_playing")
    fun listNowPlaying(
        @Query("language") language: String,
        @Query("api_key") api_key: String
    ): Call<Filmes>
//    https://api.themoviedb.org/3/movie/now_playing?api_key=f321a808e68611f41312aa8408531476&language=pt-BR

    @GET("3/genre/movie/list")
    fun listGenres(
        @Query("language") language: String,
        @Query("api_key") api_key: String
    ): Call<Generos>
//    https://api.themoviedb.org/3/genre/movie/list?api_key=f321a808e68611f41312aa8408531476&language=pt-BR

    @GET("3/search/movie")
    fun listBusca(
        @Query("language") language: String,
        @Query("api_key") api_key: String,
        @Query("query") query: String
    ): Call<Filmes>
//    https://api.themoviedb.org/3/search/movie?api_key=f321a808e68611f41312aa8408531476&language=pt-BR&query=fast
}