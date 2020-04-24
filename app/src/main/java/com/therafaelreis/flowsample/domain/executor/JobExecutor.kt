package com.therafaelreis.flowsample.domain.executor

import com.therafaelreis.flowsample.domain.model.Resource
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlin.coroutines.CoroutineContext

// Abstraction layer for the use cases
abstract class JobExecutor<T>(private val coroutineContext: CoroutineContext) {

    abstract suspend fun getDataFlow(data: Map<String, Any>? = null): Flow<Resource<T>>
    abstract suspend fun sendToPresentation(data: Resource<T>): Resource<T>

    /**
     * @param data from the data layer
     *
     * Flow is created between domain layer and the presentation layer and any manipulation on
     * data before sending to the presentation layer can be done.
     */
    fun produces(data: Map<String, Any>? = null): Flow<Resource<T>> {
        return flow {
            val resource = getDataFlow(data)
            resource.collect {
                sendToPresentation(it)
            }
        }
    }
}