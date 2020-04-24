package com.therafaelreis.flowsample.domain.interactor

import com.therafaelreis.flowsample.domain.executor.JobExecutor
import com.therafaelreis.flowsample.domain.model.Claim
import com.therafaelreis.flowsample.domain.model.Resource
import com.therafaelreis.flowsample.domain.repository.ClaimRepository
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.flow.Flow
import kotlin.coroutines.CoroutineContext

class GetClaimUseCase(private val coroutineContext: CoroutineContext,
                      private val repository: ClaimRepository): JobExecutor<Claim>(coroutineContext){

    override suspend fun getDataFlow(data: Map<String, Any>?): Flow<Resource<Claim>> {
        return repository.getClaims()
    }

    override suspend fun sendToPresentation(data: Resource<Claim>): Resource<Claim> {
        return data
    }

    suspend fun getClaim(): Flow<Resource<Claim>>{
        val data = hashMapOf<String, String>()
        return getDataFlow(data)
    }
}