package com.therafaelreis.flowsample.presentation.mapper

import com.therafaelreis.flowsample.domain.mapper.Mapper
import com.therafaelreis.flowsample.domain.model.Policy
import com.therafaelreis.flowsample.domain.model.Resource
import com.therafaelreis.flowsample.presentation.model.DataEntity
import com.therafaelreis.flowsample.presentation.model.DataError
import com.therafaelreis.flowsample.presentation.model.policy.PolicyView

class PolicyEntityMapper : Mapper<Resource<Policy>, DataEntity<PolicyView>>() {

    override fun mapFrom(data: Resource<Policy>): DataEntity<PolicyView> {
        return when (data) {
            is Resource.SUCCESS -> DataEntity.SUCCESS(data.data?.let { mapClaimToPresentation(it) })
            is Resource.ERROR -> DataEntity.ERROR(error = DataError(data.error.message))
            is Resource.LOADING -> DataEntity.LOADING()
        }
    }

    private fun mapClaimToPresentation(model: Policy): PolicyView = PolicyView(
        policyNumber = model.policyNumber,
        vehicle = model.vehicle
    )
}