import com.therafaelreis.flowsample.data.repository.ClaimRemoteImpl
import com.therafaelreis.flowsample.data.repository.ClaimRepositoryImpl
import com.therafaelreis.flowsample.data.service.ClaimApi
import com.therafaelreis.flowsample.domain.interactor.GetClaimUseCase
import com.therafaelreis.flowsample.domain.repository.ClaimRepository
import com.therafaelreis.flowsample.presentation.di.createNetworkClient
import com.therafaelreis.flowsample.presentation.mapper.ClaimEntityMapper
import com.therafaelreis.flowsample.presentation.viewmodel.ClaimViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit

private const val BASE_URL = "https://api.npoint.io"
private const val RETROFIT_INSTANCE = "retrofit"
private const val API = "claimApi"
private const val REMOTE_CLAIM = "remote"
private const val GET_CLAIM_USE_CASE = "getClaimUseCase"

val repositoryModules = module {
    single(named(REMOTE_CLAIM)) { ClaimRemoteImpl(get(named(API)))}
    single { ClaimRepositoryImpl(get(named(REMOTE_CLAIM))) as ClaimRepository }
}

val useCaseModules = module {
    factory(named(GET_CLAIM_USE_CASE)){ GetClaimUseCase(coroutineContext = Dispatchers.Default, repository = get()) }
}

val networkModules = module {
    single(named(RETROFIT_INSTANCE)) { createNetworkClient(BASE_URL) }
    single(named(API)) { (get(named(RETROFIT_INSTANCE)) as Retrofit).create(ClaimApi::class.java) }
}

val viewModelModules = module {
    viewModel {
        ClaimViewModel(claimsUseCase = get(named(GET_CLAIM_USE_CASE)), mapper = ClaimEntityMapper())
    }
}


