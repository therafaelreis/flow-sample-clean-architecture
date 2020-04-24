package com.therafaelreis.flowsample.data.service

import com.therafaelreis.flowsample.data.model.ClaimResponseData
import retrofit2.http.GET

interface ClaimApi {

    @GET("/8a91fb0b55dc8a6ef41e.json")
    suspend fun getClaim(): ClaimResponseData
}