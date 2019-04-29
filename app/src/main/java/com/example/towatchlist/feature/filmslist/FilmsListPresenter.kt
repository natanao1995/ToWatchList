package com.example.towatchlist.feature.filmslist

import com.example.towatchlist.architecture.base.BasePresenter
import com.example.towatchlist.architecture.base.Result
import kotlinx.coroutines.launch

class FilmsListPresenter(
    private val interactor: IFilmsListInteractor
) : BasePresenter<FilmsListContract.View>(), FilmsListContract.Presenter {

    override fun searchFilm(query: String) {
        launch {
            val result = interactor.searchFilm(query)
            if (result is Result.Success) {
                view?.showResultsOfSearch(result.data.results.firstOrNull()?.title ?: "No Title for first result")
            } else if (result is Result.Error){
                view?.showResultsOfSearch(result.exception.toString())
            }
        }
    }
}