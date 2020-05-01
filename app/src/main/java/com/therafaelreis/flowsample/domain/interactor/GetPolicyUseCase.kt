package com.therafaelreis.flowsample.domain.interactor

import com.therafaelreis.flowsample.domain.executor.JobExecutor
import com.therafaelreis.flowsample.domain.model.Policy
import com.therafaelreis.flowsample.domain.model.Resource
import com.therafaelreis.flowsample.domain.repository.PolicyRepository
import kotlinx.coroutines.flow.Flow
import kotlin.coroutines.CoroutineContext


class GetPolicyUseCase(coroutineContext: CoroutineContext,
    private val repository: PolicyRepository) : JobExecutor<Policy>(coroutineContext){

    override suspend fun getDataFlow(data: Map<String, Any>?): Flow<Resource<Policy>> {
        return repository.getPolicy()
    }

    override suspend fun sendToPresentation(data: Resource<Policy>): Resource<Policy> {
        return data
    }

    suspend fun getPolicy(): Flow<Resource<Policy>>{
        val data = hashMapOf<String, String>()
        return getDataFlow(data)
    }
}