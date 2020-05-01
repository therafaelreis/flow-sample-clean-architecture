package com.therafaelreis.flowsample.data.repository.claim

import com.therafaelreis.flowsample.domain.model.Claim
import com.therafaelreis.flowsample.domain.model.Resource
import com.therafaelreis.flowsample.domain.repository.ClaimRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

class ClaimRepositoryImpl(private val repository: ClaimRemoteImpl) : ClaimRepository {

    override suspend fun getClaims(): Flow<Resource<Claim>> {
        return flow {
            repository.getClaims().collect {
                when (it) {
                    is Resource.SUCCESS -> {
                        emit(it)
                    }
                    is Resource.LOADING->{
                        emit(Resource.LOADING())
                    }
                    is Resource.ERROR -> {
                        emit(it)
                    }

                }
            }
        }
    }
}