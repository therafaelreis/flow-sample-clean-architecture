package com.therafaelreis.flowsample.domain.repository

import com.therafaelreis.flowsample.domain.model.Claim
import com.therafaelreis.flowsample.domain.model.Resource
import kotlinx.coroutines.flow.Flow

interface ClaimRepository {

    suspend fun getClaims(): Flow<Resource<Claim>>
}