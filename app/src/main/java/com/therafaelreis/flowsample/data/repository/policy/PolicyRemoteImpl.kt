package com.therafaelreis.flowsample.data.repository.policy

import com.therafaelreis.flowsample.data.mapper.PolicyDataEntityMapper
import com.therafaelreis.flowsample.data.service.PolicyApi
import com.therafaelreis.flowsample.domain.model.Policy
import com.therafaelreis.flowsample.domain.model.Resource
import com.therafaelreis.flowsample.domain.model.ResourceError
import com.therafaelreis.flowsample.domain.repository.PolicyRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class PolicyRemoteImpl constructor(private val api: PolicyApi): PolicyRepository{

    private val claimMapper = PolicyDataEntityMapper()

    override suspend fun getPolicy(): Flow<Resource<Policy>> {
        return flow{
            try{
                emit(Resource.LOADING())
                delay(7_000) // delay so it can fake an api long response
                val policy = api.getPolicy()
                emit(Resource.SUCCESS(claimMapper.mapClaimToEntity(policy)))
            }catch (e:Exception){
                emit(Resource.ERROR(ResourceError(e.message)))
            }
        }
    }
}