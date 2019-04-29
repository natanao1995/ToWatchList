package com.example.towatchlist.feature.filmslist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.towatchlist.R
import org.koin.android.ext.android.inject

class FilmsListActivity : AppCompatActivity(), FilmsListContract.View {

    private val presenter: FilmsListContract.Presenter by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_films_list)

        presenter.attachView(this)

        presenter.searchFilm("Harry Potter")
    }

    override fun showResultsOfSearch(result: String) {
        Toast.makeText(this, result, Toast.LENGTH_LONG).show()
    }
}
