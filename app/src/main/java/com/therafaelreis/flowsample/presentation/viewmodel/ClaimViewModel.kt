package com.therafaelreis.flowsample.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.therafaelreis.flowsample.domain.interactor.GetClaimUseCase
import com.therafaelreis.flowsample.domain.mapper.Mapper
import com.therafaelreis.flowsample.domain.model.Claim
import com.therafaelreis.flowsample.domain.model.Resource
import com.therafaelreis.flowsample.presentation.common.BaseViewModel
import com.therafaelreis.flowsample.presentation.model.ClaimView
import com.therafaelreis.flowsample.presentation.model.DataEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ClaimViewModel(private val claimsUseCase: GetClaimUseCase,
                     private val mapper: Mapper<Resource<Claim>, DataEntity<ClaimView>>
                     ) : BaseViewModel(){

    private val _claims = MutableLiveData<DataEntity<ClaimView>>()
    val claim : LiveData<DataEntity<ClaimView>> get() = _claims

    fun fetchClaim(){
        viewModelScope.launch {
            val claim = claimsUseCase.getClaim()

            claim.collect { response->
                val mappedResponse = mapper.mapFrom(response)

                withContext(Dispatchers.Main){
                    _claims.postValue(mappedResponse)
                }
            }
        }
    }

}