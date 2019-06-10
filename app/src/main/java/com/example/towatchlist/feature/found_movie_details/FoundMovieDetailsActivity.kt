package com.example.towatchlist.feature.found_movie_details

import android.os.Bundle
import com.example.towatchlist.R
import com.example.towatchlist.architecture.base.BaseActivity
import org.koin.android.viewmodel.ext.android.viewModel

class FoundMovieDetailsActivity : BaseActivity(), FoundMovieDetailsContract.View {

    private val presenter by viewModel<FoundMovieDetailsContract.Presenter>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_found_movie_details)

        presenter.attachView(this, lifecycle)
    }
}
