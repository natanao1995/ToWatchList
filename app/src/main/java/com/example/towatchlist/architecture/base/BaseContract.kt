package com.example.towatchlist.architecture.base

import androidx.lifecycle.Lifecycle

interface BaseContract {
    interface Presenter<T : BaseContract.View> {
        fun attachView(view: T, viewLifecycle: Lifecycle)
    }

    interface View
}