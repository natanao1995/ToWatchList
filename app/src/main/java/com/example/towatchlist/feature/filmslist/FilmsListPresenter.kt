package com.example.towatchlist.feature.filmslist

import android.content.Context
import com.example.towatchlist.architecture.base.BasePresenter
import com.example.towatchlist.model.remote.TMDbService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FilmsListPresenter(
    private val tmDbService: TMDbService
) : BasePresenter<FilmsListContract.View>(), FilmsListContract.Presenter {

    override fun searchFilm(query: String) {
        launch(Dispatchers.IO) {
            val response = tmDbService.searchMovie("Harry").execute()
            withContext(Dispatchers.Main) {
                view?.showResultsOfSearch(response.toString())
            }
        }
    }
}