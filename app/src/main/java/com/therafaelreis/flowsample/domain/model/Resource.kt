package com.therafaelreis.flowsample.domain.model

sealed class Resource<T> {
    class SUCCESS<T>(var data: T? = null) : Resource<T>()
    class LOADING<T>(var data: T? = null) : Resource<T>()
    class ERROR<T>(var error: ResourceError, var data: T? = null) : Resource<T>()

}

class ResourceError(var message: String? = null)