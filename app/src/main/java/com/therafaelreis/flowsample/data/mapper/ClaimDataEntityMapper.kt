package com.therafaelreis.flowsample.data.mapper

import com.therafaelreis.flowsample.data.model.claim.ClaimResponseData
import com.therafaelreis.flowsample.domain.model.Claim

class ClaimDataEntityMapper{

    fun mapClaimToEntity(response: ClaimResponseData): Claim = Claim(claimNumber = response.claimNumber)
}