package com.example.towatchlist.feature.find_movies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.towatchlist.R
import com.example.towatchlist.feature.find_movies.recycler.FindMoviesRecyclerAdapter
import com.example.towatchlist.model.remote.entity.SearchMovieResponseEntity
import kotlinx.android.synthetic.main.activity_find_movies.*
import org.koin.android.ext.android.inject

class FindMoviesActivity : AppCompatActivity(), FindMoviesContract.View {

    private val presenter: FindMoviesContract.Presenter by inject()
    private val adapter = FindMoviesRecyclerAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_find_movies)

        presenter.attachView(this, lifecycle)

        presenter.searchMovie("Harry Potter")

        recyclerFindMovies.layoutManager = LinearLayoutManager(this)
        recyclerFindMovies.adapter = adapter
    }

    override fun showSearchResults(result: List<SearchMovieResponseEntity.SearchMovieResponseResult>) {
        adapter.setItems(result)
    }

    override fun showSearchError() {
        adapter.clearItems()
        Toast.makeText(this, "Search error", Toast.LENGTH_SHORT).show()
    }
}
