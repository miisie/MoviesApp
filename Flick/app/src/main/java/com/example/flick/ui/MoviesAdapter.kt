package com.example.flick.ui

import android.content.res.Configuration
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.example.flick.R
import com.example.flick.database.MovieDetails
import kotlinx.android.synthetic.main.movie_intro.view.*

class MoviesAdapter(
    private val orientation: Int,
    private var movies: MutableList<MovieDetails>,
    private val onMovieClick: (movie: MovieDetails) -> Unit
) : RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.movie_intro, parent, false)
        return MovieViewHolder(view)
    }

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    fun appendMovies(movies: MutableList<MovieDetails>) {
        this.movies.addAll(movies)
        notifyItemRangeInserted(
            this.movies.size,
            movies.size - 1
        )
    }
    fun clear(){
        movies.clear()
        notifyDataSetChanged()
    }

    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val poster: ImageView = itemView.findViewById(R.id.item_movie_poster)
        
        fun bind(movie: MovieDetails) {
            itemView.item_movie_title.text = movie.title + ":"
            itemView.item_movie_overview.text =  movie.overview
            if(orientation == Configuration.ORIENTATION_PORTRAIT) {
                Glide.with(itemView)
                    .load("https://image.tmdb.org/t/p/w342${movie.posterPath}")
                    .transform(CenterCrop())
                    .into(poster)
            }
            else {
                Glide.with(itemView)
                    .load("https://image.tmdb.org/t/p/w342${movie.backdropPath}")
                    .transform(CenterCrop())
                    .into(poster)
            }
            itemView.setOnClickListener { onMovieClick.invoke(movie) }
        }
    }
}

