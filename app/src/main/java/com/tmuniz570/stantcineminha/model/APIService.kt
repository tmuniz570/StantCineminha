package com.tmuniz570.stantcineminha.model

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {
    @GET("3/movie/now_playing")
    fun listNowPlaying(
        @Query("language") language: String,
        @Query("api_key") api_key: String
    ): Call<NowPlaying>

    @GET("3/genre/movie/list")
    fun listGenres(
        @Query("language") language: String,
        @Query("api_key") api_key: String
    ): Call<Generos>
}