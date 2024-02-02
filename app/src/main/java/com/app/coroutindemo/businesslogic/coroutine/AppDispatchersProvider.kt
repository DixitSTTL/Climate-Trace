package com.app.paper.businesslogic.coroutine

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class AppDispatchersProvider : DispatchersProvider {

    override fun ui(): CoroutineDispatcher {
        return Dispatchers.Main
    }

    override fun computation(): CoroutineDispatcher {
        return Dispatchers.Default
    }

    override fun io(): CoroutineDispatcher {
        return Dispatchers.IO
    }
}