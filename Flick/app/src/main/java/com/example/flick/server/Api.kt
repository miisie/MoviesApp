package com.example.flick.server

import com.example.flick.database.GetMoviesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("movie/popular")
    fun getPopularMovies(
        @Query("api_key") apiKey: String = "ed194678c929e8b1ea62c1b7d0cb112a",
        @Query("page") page: Int
    ): Call<GetMoviesResponse>
}

