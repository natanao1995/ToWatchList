package com.example.towatchlist.architecture.base

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*

abstract class BasePresenter<T : MvpView> : ViewModel(), LifecycleObserver, CoroutineScope by MainScope() {

    protected var view: T? = null
        private set

    private var viewLifecycle: Lifecycle? = null

    fun attachView(view: T, viewLifecycle: Lifecycle) {
        this.view = view
        this.viewLifecycle = viewLifecycle

        viewLifecycle.addObserver(this)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    private fun onViewDestroyed() {
        view = null
        viewLifecycle = null
    }

    override fun onCleared() {
        super.onCleared()
        cancel()
    }
}