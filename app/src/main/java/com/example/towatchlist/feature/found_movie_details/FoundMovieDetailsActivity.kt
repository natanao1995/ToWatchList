package com.example.towatchlist.feature.found_movie_details

import android.os.Bundle
import com.example.towatchlist.R
import com.example.towatchlist.architecture.base.BaseActivity
import com.example.towatchlist.model.remote.entity.SearchMovieResponseEntity
import org.koin.android.viewmodel.ext.android.viewModel
import kotlinx.android.synthetic.main.item_find_movies.*
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.Glide
import android.graphics.drawable.Drawable
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.example.towatchlist.Constants

class FoundMovieDetailsActivity : BaseActivity(), FoundMovieDetailsContract.View {

    companion object {
        const val EXTRA_MOVIE_ITEM = "extra_movie_item"
        const val EXTRA_IMAGE_POSTER_TRANSITION_NAME = "extra_image_poster_transition_name"
        const val EXTRA_VIEW_BG_TRANSITION_NAME = "extra_view_bg_transition_name"
    }

    private val presenter by viewModel<FoundMovieDetailsContract.Presenter>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_found_movie_details)
        supportPostponeEnterTransition()

        val extras = intent.extras
        val movie = extras?.getParcelable<SearchMovieResponseEntity.SearchMovieResponseResult>(EXTRA_MOVIE_ITEM)

        imagePoster.transitionName = extras?.getString(EXTRA_IMAGE_POSTER_TRANSITION_NAME)
        viewBg.transitionName = extras?.getString(EXTRA_VIEW_BG_TRANSITION_NAME)

        Glide.with(this)
            .load("${Constants.BASE_URL_TMDB_IMAGE}${movie?.posterPath}")
            .centerCrop()
            .dontAnimate()
            .listener(object : RequestListener<Drawable> {
                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: com.bumptech.glide.request.target.Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    supportStartPostponedEnterTransition()
                    return false
                }

                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: com.bumptech.glide.request.target.Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    supportStartPostponedEnterTransition()
                    return false
                }
            })
            .into(imagePoster)

        presenter.attachView(this, lifecycle)
    }
}
