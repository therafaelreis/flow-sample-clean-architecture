package com.therafaelreis.flowsample.presentation.model

sealed class DataEntity<T> {
    class SUCCESS<T>(var data: T? = null) : DataEntity<T>()

    class ERROR<T>(var error: DataError?, var data: T? = null) : DataEntity<T>()

    class LOADING<T>(var data: T? = null) : DataEntity<T>()

}

data class DataError(var message: String? = null)
