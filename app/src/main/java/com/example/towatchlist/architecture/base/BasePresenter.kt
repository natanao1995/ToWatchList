package com.example.towatchlist.architecture.base

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

open class BasePresenter<T : BaseContract.View> : BaseContract.Presenter<T>, CoroutineScope {
    var job: Job = Job()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    protected var view: T? = null
        private set

    override fun attachView(view: T) {
        this.view = view
    }

    override fun detachView() {
        view = null
        job.cancel()
    }
}