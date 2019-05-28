package com.example.towatchlist.feature.find_movies

import com.example.towatchlist.architecture.base.BasePresenter
import com.example.towatchlist.architecture.base.Result
import kotlinx.coroutines.launch

class FindMoviesPresenter(
    private val interactorFind: IFindMoviesInteractor
) : BasePresenter<FindMoviesContract.View>(), FindMoviesContract.Presenter {

    override fun searchMovie(query: String) {
        launch {
            val result = interactorFind.searchMovie(query)
            if (result is Result.Success) {
                view?.showSearchResults(result.data.results)
            } else if (result is Result.Error){
                view?.showSearchError()
            }
        }
    }
}