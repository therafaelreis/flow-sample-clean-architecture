package com.therafaelreis.flowsample.data.model

sealed class DataResponse<T> {
    class SUCCESS<T>(var data: T? = null) : DataResponse<T>()

    class ERROR<T>(var error: Error? = null, var data: T? = null) : DataResponse<T>()

    class LOADING<T>(var data: T? = null) : DataResponse<T>()
}

sealed class Error(val message: String? = null)
