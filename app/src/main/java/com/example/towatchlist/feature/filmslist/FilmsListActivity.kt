package com.example.towatchlist.feature.filmslist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.towatchlist.R
import com.example.towatchlist.architecture.di.component.DaggerActivityComponent
import com.example.towatchlist.architecture.di.module.ActivityModule
import javax.inject.Inject

class FilmsListActivity : AppCompatActivity(), FilmsListContract.View {
    @Inject
    lateinit var presenter: FilmsListContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_films_list)

        injectDependency()
        presenter.attachView(this)

        presenter.searchFilm("Harry Potter")
    }

    override fun showResultsOfSearch(result: String) {
        Toast.makeText(this, result, Toast.LENGTH_LONG).show()
    }

    private fun injectDependency() {
        val activityComponent = DaggerActivityComponent.builder()
            .activityModule(ActivityModule(this))
            .build()

        activityComponent.inject(this)
    }
}
