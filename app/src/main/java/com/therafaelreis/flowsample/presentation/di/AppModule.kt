import com.therafaelreis.flowsample.data.repository.claim.ClaimRemoteImpl
import com.therafaelreis.flowsample.data.repository.claim.ClaimRepositoryImpl
import com.therafaelreis.flowsample.data.repository.policy.PolicyRemoteImpl
import com.therafaelreis.flowsample.data.repository.policy.PolicyRepositoryImpl
import com.therafaelreis.flowsample.data.service.ClaimApi
import com.therafaelreis.flowsample.data.service.PolicyApi
import com.therafaelreis.flowsample.domain.interactor.GetClaimUseCase
import com.therafaelreis.flowsample.domain.interactor.GetPolicyUseCase
import com.therafaelreis.flowsample.domain.repository.ClaimRepository
import com.therafaelreis.flowsample.domain.repository.PolicyRepository
import com.therafaelreis.flowsample.presentation.di.createNetworkClient
import com.therafaelreis.flowsample.presentation.mapper.ClaimEntityMapper
import com.therafaelreis.flowsample.presentation.mapper.PolicyEntityMapper
import com.therafaelreis.flowsample.presentation.viewmodel.ClaimViewModel
import com.therafaelreis.flowsample.presentation.viewmodel.PolicyViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit

private const val BASE_URL = "https://api.npoint.io"
private const val RETROFIT_INSTANCE = "retrofit"

// retrofit interfaces
private const val CLAIM_API = "claimApi"
private const val POLICY_API = "policyApi"

// repo impl
private const val REMOTE_POLICY = "policyRemote"
private const val REMOTE_CLAIM = "remote"

// use cases
private const val GET_CLAIM_USE_CASE = "getClaimUseCase"
private const val GET_POLICY_USE_CASE = "getPolicyUseCase"

val repositoryModules = module {
    // claims repo
    single(named(REMOTE_CLAIM)) { ClaimRemoteImpl(get(named(CLAIM_API))) }
    single { ClaimRepositoryImpl(get(named(REMOTE_CLAIM))) as ClaimRepository }

    // policy repo
    single(named(REMOTE_POLICY)) { PolicyRemoteImpl(get(named(POLICY_API))) }
    single { PolicyRepositoryImpl(get(named(REMOTE_POLICY))) as PolicyRepository }
}

val useCaseModules = module {
    factory(named(GET_CLAIM_USE_CASE)) {
        GetClaimUseCase(coroutineContext = Dispatchers.Default, repository = get())
    }

    factory(named(GET_POLICY_USE_CASE)) {
        GetPolicyUseCase(coroutineContext = Dispatchers.Default, repository = get())
    }
}

val networkModules = module {
    single(named(RETROFIT_INSTANCE)) { createNetworkClient(BASE_URL) }
    single(named(CLAIM_API)) { (get(named(RETROFIT_INSTANCE)) as Retrofit).create(ClaimApi::class.java) }
    single(named(POLICY_API)) { (get(named(RETROFIT_INSTANCE)) as Retrofit).create(PolicyApi::class.java) }

}

val viewModelModules = module {
    viewModel {
        ClaimViewModel(
            claimsUseCase = get(named(GET_CLAIM_USE_CASE)),
            mapper = ClaimEntityMapper()
        )
    }

    viewModel {
        PolicyViewModel(
            policyUseCase = get(named(GET_POLICY_USE_CASE)),
            mapper = PolicyEntityMapper()
        )
    }
}


