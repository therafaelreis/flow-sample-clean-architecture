package com.therafaelreis.flowsample.data.repository

import com.therafaelreis.flowsample.data.mapper.ClaimDataEntityMapper
import com.therafaelreis.flowsample.data.service.ClaimApi
import com.therafaelreis.flowsample.domain.model.Claim
import com.therafaelreis.flowsample.domain.model.Resource
import com.therafaelreis.flowsample.domain.model.ResourceError
import com.therafaelreis.flowsample.domain.repository.ClaimRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ClaimRemoteImpl  constructor(private val api: ClaimApi): ClaimRepository{

    private val claimMapper = ClaimDataEntityMapper()

    override suspend fun getClaims(): Flow<Resource<Claim>> {
        return flow{
            try{
                val claim = api.getClaim()
                emit(Resource.SUCCESS(claimMapper.mapClaimToEntity(claim)))

            }catch (e:Exception){
                emit(Resource.ERROR(ResourceError(e.message)))
            }
        }
    }
}