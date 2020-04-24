package com.therafaelreis.flowsample.data.model

import com.google.gson.annotations.SerializedName

data class ClaimResponseData(
    @SerializedName("status") var status: String? = null,
    @SerializedName("claimNumber") var claimNumber: String? = null
)
