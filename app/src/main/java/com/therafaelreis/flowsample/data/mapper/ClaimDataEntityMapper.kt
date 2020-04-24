package com.therafaelreis.flowsample.data.mapper

import com.therafaelreis.flowsample.data.model.ClaimResponseData
import com.therafaelreis.flowsample.domain.model.Claim
import com.therafaelreis.flowsample.presentation.model.ClaimView
import com.therafaelreis.flowsample.presentation.model.DataEntity

class ClaimDataEntityMapper{

    fun mapClaimToEntity(response: ClaimResponseData): Claim = Claim(claimNumber = response.claimNumber)

    fun mapResponseToData(response: DataEntity<Claim>): ClaimView? {
        when (response) {
            is DataEntity.SUCCESS<Claim> -> {
                return response.data?.let { mapClaimToClaimView(it) }
            }
            is DataEntity.ERROR<Claim> ->
                return response.data?.let { mapClaimToClaimView(it) }
            is DataEntity.LOADING<Claim> ->
                return response.data?.let { mapClaimToClaimView(it) }
        }
    }

    fun mapClaimToClaimView(response: Claim): ClaimView = ClaimView(claimNumber = response.claimNumber)
}