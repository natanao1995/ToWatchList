package com.example.towatchlist.feature.find_movies

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.towatchlist.R
import com.example.towatchlist.feature.find_movies.recycler.FindMoviesRecyclerAdapter
import com.example.towatchlist.model.remote.entity.SearchMovieResponseEntity
import kotlinx.android.synthetic.main.activity_find_movies.*
import com.example.towatchlist.architecture.base.BaseActivity
import com.example.towatchlist.feature.found_movie_details.FoundMovieDetailsActivity
import com.example.towatchlist.feature.found_movie_details.FoundMovieDetailsActivity.Companion.EXTRA_IMAGE_POSTER_TRANSITION_NAME
import com.example.towatchlist.feature.found_movie_details.FoundMovieDetailsActivity.Companion.EXTRA_MOVIE_ITEM
import com.example.towatchlist.feature.found_movie_details.FoundMovieDetailsActivity.Companion.EXTRA_VIEW_BG_TRANSITION_NAME
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

        buttonOpenNewActivity.setOnClickListener {
            startActivity(Intent(this, FoundMovieDetailsActivity::class.java))
        }

        adapter.onClickListener = {movie, imagePoster, viewBg ->
            val intent = Intent(this, FoundMovieDetailsActivity::class.java)
            intent.putExtra(EXTRA_MOVIE_ITEM, movie)
            intent.putExtra(EXTRA_IMAGE_POSTER_TRANSITION_NAME, imagePoster.transitionName)
            intent.putExtra(EXTRA_VIEW_BG_TRANSITION_NAME, viewBg.transitionName)

            val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                this,
                Pair(imagePoster as View, imagePoster.transitionName),
                Pair(viewBg, viewBg.transitionName)
            )

            startActivity(intent, options.toBundle())
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
