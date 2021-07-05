package com.example.flick.database

import com.google.gson.annotations.SerializedName

data class GetMoviesResponse(
    @SerializedName("page") val page: Int,
    @SerializedName("results") val movies: MutableList<MovieDetails>,
    @SerializedName("total_pages") val pages: Int
)

