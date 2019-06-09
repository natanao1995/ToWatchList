package com.example.towatchlist.feature.find_movies

import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.towatchlist.R
import com.example.towatchlist.feature.find_movies.recycler.FindMoviesRecyclerAdapter
import com.example.towatchlist.model.remote.entity.SearchMovieResponseEntity
import kotlinx.android.synthetic.main.activity_find_movies.*
import com.example.towatchlist.architecture.base.BaseActivity
import org.koin.android.viewmodel.ext.android.viewModel

class FindMoviesActivity : BaseActivity(), FindMoviesContract.View {

    private val presenter by viewModel<FindMoviesContract.Presenter>()
    private val adapter = FindMoviesRecyclerAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_find_movies)

        presenter.attachView(this, lifecycle)
        presenter.restoreSearchResults()

        recyclerFindMovies.layoutManager = LinearLayoutManager(this)
        recyclerFindMovies.adapter = adapter

        buttonNextPage.setOnClickListener {
            presenter.appendSearchResult()
        }

        buttonSearch.setOnClickListener {
            presenter.searchMovies(editTextSearch.text.toString())
            hideKeyboard()
        }
    }

    override fun showSearchResults(result: List<SearchMovieResponseEntity.SearchMovieResponseResult>) {
        adapter.setItems(result)
    }

    override fun appendSearchResults(result: List<SearchMovieResponseEntity.SearchMovieResponseResult>) {
        adapter.appendItems(result)
    }

    override fun showSearchError() {
        adapter.clearItems()
        Toast.makeText(this, "Search error", Toast.LENGTH_SHORT).show()
    }
}
