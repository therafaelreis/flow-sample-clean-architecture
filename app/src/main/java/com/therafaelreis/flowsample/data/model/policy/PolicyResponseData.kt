package com.therafaelreis.flowsample.data.model.policy

import com.google.gson.annotations.SerializedName

data class PolicyResponseData(
    @SerializedName("status") var status: String? = null,
    @SerializedName("policyNumber") var policyNumber: String? = null,
    @SerializedName("vehicle") var vehicle: String? = null
)