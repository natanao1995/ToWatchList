package com.example.towatchlist.feature.find_movies.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.towatchlist.Constants.BASE_URL_TMDB_IMAGE
import com.example.towatchlist.R
import com.example.towatchlist.model.remote.entity.SearchMovieResponseEntity
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_find_movies.*
import java.util.*

class FindMoviesRecyclerAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items: ArrayList<SearchMovieResponseEntity.SearchMovieResponseResult> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_find_movies, parent, false)
        return FindMoviesViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val movie = items[position]
        val movieHolder = holder as FindMoviesViewHolder

        movieHolder.imagePoster.setImageDrawable(null)
        movie.posterPath?.let {
            Glide.with(holder.itemView)
                .load("$BASE_URL_TMDB_IMAGE$it")
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(movieHolder.imagePoster)
        }

        movieHolder.textTitle.text = movie.title
        movieHolder.textDescription.text = movie.overview
        movieHolder.textScore.text = movie.voteAverage.toString()
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun setItems(items: List<SearchMovieResponseEntity.SearchMovieResponseResult>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    fun appendItems(items: List<SearchMovieResponseEntity.SearchMovieResponseResult>) {
        val positionStart = itemCount
        this.items.addAll(items)
        notifyItemRangeInserted(positionStart, items.size)
    }

    fun clearItems() {
        val itemCount = itemCount
        this.items.clear()
        notifyItemRangeRemoved(0, itemCount)
    }

    internal inner class FindMoviesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), LayoutContainer {
        override val containerView: View?
            get() = itemView
    }
}