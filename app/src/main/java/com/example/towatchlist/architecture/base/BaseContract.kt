package com.example.towatchlist.architecture.base

interface BaseContract {
    interface Presenter<T : BaseContract.View> {
        fun attachView(view: T)
        fun detachView()
    }

    interface View
}