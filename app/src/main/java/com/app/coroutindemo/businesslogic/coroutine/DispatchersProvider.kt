package com.app.paper.businesslogic.coroutine

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

interface DispatchersProvider  {

    fun ui(): CoroutineDispatcher?

    fun computation(): CoroutineDispatcher?

    fun io(): CoroutineDispatcher?
}