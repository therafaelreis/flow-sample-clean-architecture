package com.therafaelreis.flowsample.data.mapper

import com.therafaelreis.flowsample.data.model.policy.PolicyResponseData
import com.therafaelreis.flowsample.domain.model.Policy

class PolicyDataEntityMapper{

    fun mapClaimToEntity(response: PolicyResponseData): Policy = Policy(policyNumber= response.policyNumber, vehicle = response.vehicle)
}