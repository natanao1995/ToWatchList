package com.example.towatchlist.feature.find_movies

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.towatchlist.R
import com.example.towatchlist.feature.find_movies.recycler.FindMoviesRecyclerAdapter
import com.example.towatchlist.model.remote.entity.SearchMovieResponseEntity
import kotlinx.android.synthetic.main.activity_find_movies.*
import com.example.towatchlist.architecture.base.BaseActivity
import com.example.towatchlist.feature.found_movie_details.FoundMovieDetailsActivity
import com.example.towatchlist.feature.found_movie_details.FoundMovieDetailsActivity.Companion.EXTRA_TRANSITION_NAME_IMAGE_POSTER
import com.example.towatchlist.feature.found_movie_details.FoundMovieDetailsActivity.Companion.EXTRA_MOVIE_ITEM
import com.example.towatchlist.feature.found_movie_details.FoundMovieDetailsActivity.Companion.EXTRA_TRANSITION_NAME_TEXT_DESCRIPTION
import com.example.towatchlist.feature.found_movie_details.FoundMovieDetailsActivity.Companion.EXTRA_TRANSITION_NAME_TEXT_TITLE
import com.example.towatchlist.feature.found_movie_details.FoundMovieDetailsActivity.Companion.EXTRA_TRANSITION_NAME_VIEW_BG
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
