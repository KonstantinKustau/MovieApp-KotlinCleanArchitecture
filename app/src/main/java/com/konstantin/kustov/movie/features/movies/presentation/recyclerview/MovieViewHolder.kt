package com.konstantin.kustov.movie.features.movies.presentation.recyclerview

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(
        movieView: MovieView,
        clickListener: (MovieView) -> Unit
    ) {
        Picasso.get().load(movieView.poster).into(itemView.itemMovieImage)
        itemView.itemMovieName.text = movieView.title
        itemView.setOnClickListener {
            clickListener(movieView)
        }
    }
}