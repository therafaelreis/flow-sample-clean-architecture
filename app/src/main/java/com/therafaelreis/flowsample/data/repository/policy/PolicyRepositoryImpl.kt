package com.therafaelreis.flowsample.data.repository.policy

import com.therafaelreis.flowsample.domain.model.Policy
import com.therafaelreis.flowsample.domain.model.Resource
import com.therafaelreis.flowsample.domain.repository.PolicyRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

class PolicyRepositoryImpl(private val repository: PolicyRemoteImpl) : PolicyRepository {

    override suspend fun getPolicy(): Flow<Resource<Policy>> {
        return flow {
            repository.getPolicy().collect {
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