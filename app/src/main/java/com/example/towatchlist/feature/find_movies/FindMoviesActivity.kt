package com.example.towatchlist.feature.find_movies

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.towatchlist.R
import com.example.towatchlist.feature.find_movies.recycler.FindMoviesRecyclerAdapter
import kotlinx.android.synthetic.main.activity_find_movies.*
import com.example.towatchlist.architecture.base.BaseActivity
import com.example.towatchlist.feature.found_movie_details.FoundMovieDetailsActivity
import com.example.towatchlist.feature.found_movie_details.FoundMovieDetailsActivity.Companion.EXTRA_TRANSITION_NAME_IMAGE_POSTER
import com.example.towatchlist.feature.found_movie_details.FoundMovieDetailsActivity.Companion.EXTRA_MOVIE_ITEM
import com.example.towatchlist.feature.found_movie_details.FoundMovieDetailsActivity.Companion.EXTRA_TRANSITION_NAME_TEXT_DESCRIPTION
import com.example.towatchlist.feature.found_movie_details.FoundMovieDetailsActivity.Companion.EXTRA_TRANSITION_NAME_TEXT_TITLE
import com.example.towatchlist.feature.found_movie_details.FoundMovieDetailsActivity.Companion.EXTRA_TRANSITION_NAME_VIEW_BG
import org.koin.android.viewmodel.ext.android.viewModel

class FindMoviesActivity : BaseActivity() {

    private val viewModel by viewModel<FindMoviesViewModel>()
    private val adapter = FindMoviesRecyclerAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_find_movies)

        viewModel.attachView(lifecycle)
        viewModel.restoreSearchResults()

        setupObserve()

        recyclerFindMovies.layoutManager = LinearLayoutManager(this)
        recyclerFindMovies.adapter = adapter

        buttonNextPage.setOnClickListener {
            viewModel.appendSearchResult()
        }

        buttonSearch.setOnClickListener {
            viewModel.searchMovies(editTextSearch.text.toString())
            hideKeyboard()
        }

        adapter.onClickListener = {movie, imagePoster, viewBg, textTitle, textDescription ->
            val intent = Intent(this, FoundMovieDetailsActivity::class.java)
            intent.putExtra(EXTRA_MOVIE_ITEM, movie)
            intent.putExtra(EXTRA_TRANSITION_NAME_IMAGE_POSTER, imagePoster.transitionName)
            intent.putExtra(EXTRA_TRANSITION_NAME_VIEW_BG, viewBg.transitionName)
            intent.putExtra(EXTRA_TRANSITION_NAME_TEXT_TITLE, textTitle.transitionName)
            intent.putExtra(EXTRA_TRANSITION_NAME_TEXT_DESCRIPTION, textDescription.transitionName)

            val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                this,
                Pair(imagePoster as View, imagePoster.transitionName),
                Pair(viewBg, viewBg.transitionName),
                Pair(textTitle as View, textTitle.transitionName),
                Pair(textDescription, textDescription.transitionName)
            )

            startActivity(intent, options.toBundle())
        }
    }

    private fun setupObserve() {
        viewModel.searchResultsLiveData.observe(this, Observer { result ->
            result ?: return@Observer

            adapter.setItems(result)
        })
        viewModel.searchResultsErrorLiveData.observe(this, Observer { error ->
            error ?: return@Observer

            Toast.makeText(this, "Search error", Toast.LENGTH_SHORT).show()
        })
    }
}
