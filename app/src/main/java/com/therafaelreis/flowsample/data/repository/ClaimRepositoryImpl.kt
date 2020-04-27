package com.therafaelreis.flowsample.data.repository

import com.therafaelreis.flowsample.domain.model.Claim
import com.therafaelreis.flowsample.domain.model.Resource
import com.therafaelreis.flowsample.domain.repository.ClaimRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

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