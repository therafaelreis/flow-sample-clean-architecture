package com.therafaelreis.flowsample.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.therafaelreis.flowsample.domain.interactor.GetPolicyUseCase
import com.therafaelreis.flowsample.domain.mapper.Mapper
import com.therafaelreis.flowsample.domain.model.Policy
import com.therafaelreis.flowsample.domain.model.Resource
import com.therafaelreis.flowsample.presentation.common.BaseViewModel
import com.therafaelreis.flowsample.presentation.model.DataEntity
import com.therafaelreis.flowsample.presentation.model.policy.PolicyView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PolicyViewModel(
    private val policyUseCase: GetPolicyUseCase,
    private val mapper: Mapper<Resource<Policy>, DataEntity<PolicyView>>
) : BaseViewModel() {


    private val _policy = MutableLiveData<DataEntity<PolicyView>>()
    val policy: LiveData<DataEntity<PolicyView>> get() = _policy

    fun fetchPolicy() {
        viewModelScope.launch {
            val policy = policyUseCase.getPolicy()

            policy.collect { response ->
                val mappedResponse = mapper.mapFrom(response)

                withContext(Dispatchers.Main) {
                    _policy.postValue(mappedResponse)
                }
            }
        }
    }
}