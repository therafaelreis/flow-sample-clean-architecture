package com.therafaelreis.flowsample.domain.repository

import com.therafaelreis.flowsample.domain.model.Policy
import com.therafaelreis.flowsample.domain.model.Resource
import kotlinx.coroutines.flow.Flow

interface PolicyRepository {

    suspend fun getPolicy(): Flow<Resource<Policy>>
}