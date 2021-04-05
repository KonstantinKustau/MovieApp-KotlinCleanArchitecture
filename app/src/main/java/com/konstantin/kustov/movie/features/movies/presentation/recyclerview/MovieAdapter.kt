package com.konstantin.kustov.movie.features.movies.presentation.recyclerview

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.konstantin.kustov.movie.R
import com.konstantin.kustov.movie.core.extension.inflate
import javax.inject.Inject
import kotlin.properties.Delegates

class MovieAdapter
@Inject constructor() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    internal var collection: ArrayList<MovieView> by Delegates.observable(arrayListOf()) { _, _, _ ->
        notifyDataSetChanged()
    }

    internal var clickListener: (MovieView) -> Unit = { _ -> }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        MovieViewHolder(parent.inflate(R.layout.item_movie))

    override fun getItemCount() = collection.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder as MovieViewHolder
        val item: MovieView = collection[position]
        holder.bind(item, clickListener)
    }
}