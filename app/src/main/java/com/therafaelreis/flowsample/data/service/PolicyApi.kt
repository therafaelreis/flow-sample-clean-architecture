package com.therafaelreis.flowsample.data.service

import com.therafaelreis.flowsample.data.model.policy.PolicyResponseData
import retrofit2.http.GET

interface PolicyApi {

    @GET("/b548cf1762f23548c442.json") //json with data
//    @GET("/c92f4eaf8175f7f84717.json") //json without data
    suspend fun getPolicy(): PolicyResponseData
}