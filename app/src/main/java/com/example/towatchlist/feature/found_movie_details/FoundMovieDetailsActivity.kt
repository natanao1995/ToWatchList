package com.example.towatchlist.feature.found_movie_details

import android.os.Bundle
import com.example.towatchlist.R
import com.example.towatchlist.architecture.base.BaseActivity
import com.example.towatchlist.model.remote.entity.MovieListResultObject
import org.koin.android.viewmodel.ext.android.viewModel
import kotlinx.android.synthetic.main.activity_found_movie_details.*
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.Glide
import android.graphics.drawable.Drawable
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.example.towatchlist.Constants
import jp.wasabeef.glide.transformations.BlurTransformation

class FoundMovieDetailsActivity : BaseActivity() {

    companion object {
        const val EXTRA_MOVIE_ITEM = "extra_movie_item"
        const val EXTRA_TRANSITION_NAME_IMAGE_POSTER = "extra_transition_name_image_poster"
        const val EXTRA_TRANSITION_NAME_VIEW_BG = "extra_transition_name_view_bg"
        const val EXTRA_TRANSITION_NAME_TEXT_TITLE = "extra_transition_name_text_title"
        const val EXTRA_TRANSITION_NAME_TEXT_DESCRIPTION = "extra_transition_name_text_description"
    }

    private val viewModel by viewModel<FoundMovieDetailsViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_found_movie_details)
        supportPostponeEnterTransition()

        val extras = intent.extras
        val movie = extras?.getParcelable<MovieListResultObject.SearchMovieResponseResult>(EXTRA_MOVIE_ITEM)

        imagePoster.transitionName = extras?.getString(EXTRA_TRANSITION_NAME_IMAGE_POSTER)
        viewBg.transitionName = extras?.getString(EXTRA_TRANSITION_NAME_VIEW_BG)
        textTitle.transitionName = extras?.getString(EXTRA_TRANSITION_NAME_TEXT_TITLE)
        textDescription.transitionName = extras?.getString(EXTRA_TRANSITION_NAME_TEXT_DESCRIPTION)

        textTitle.text = movie?.title
        textDescription.text = movie?.overview

        Glide.with(this)
            .load("${Constants.BASE_URL_TMDB_IMAGE}${movie?.posterPath}")
            .transform(MultiTransformation(CenterCrop(), BlurTransformation(25, 3)))
            .into(imageBlurred)

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

        viewModel.attachView(lifecycle)
    }
}
