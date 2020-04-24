package com.therafaelreis.flowsample.presentation.mapper

import com.therafaelreis.flowsample.domain.mapper.Mapper
import com.therafaelreis.flowsample.domain.model.Claim
import com.therafaelreis.flowsample.domain.model.Resource
import com.therafaelreis.flowsample.presentation.model.ClaimView
import com.therafaelreis.flowsample.presentation.model.DataEntity
import com.therafaelreis.flowsample.presentation.model.DataEntity.*
import com.therafaelreis.flowsample.presentation.model.DataError


class ClaimEntityMapper : Mapper<Resource<Claim>, DataEntity<ClaimView>>(){

    override fun mapFrom(data: Resource<Claim>): DataEntity<ClaimView> {
        when(data){
            is Resource.SUCCESS -> return SUCCESS(data.data?.let { mapClaimToPresentation(it) })
            is Resource.ERROR -> return ERROR(error = DataError(data.error.message))
            is Resource.LOADING -> return LOADING()
        }
    }

    private fun mapClaimToPresentation(sources: Claim)
            : ClaimView = ClaimView(
        claimNumber= sources.claimNumber)
}