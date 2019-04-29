package com.example.towatchlist.feature.filmslist

import com.example.towatchlist.architecture.base.BasePresenter
import com.example.towatchlist.architecture.base.Result
import kotlinx.coroutines.launch

class FilmsListPresenter(
    private val interactor: IFilmsListInteractor
) : BasePresenter<FilmsListContract.View>(), FilmsListContract.Presenter {

    override fun searchFilm(query: String) {
        launch {
            view?.showResultsOfSearch((interactor.searchFilm(query) as Result.Success).data)
        }
    }
}