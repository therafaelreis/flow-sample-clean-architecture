package com.therafaelreis.flowsample.presentation.view

import android.animation.ValueAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.facebook.shimmer.Shimmer
import com.therafaelreis.flowsample.R
import com.therafaelreis.flowsample.presentation.model.DataEntity
import com.therafaelreis.flowsample.presentation.viewmodel.ClaimViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.claim_card.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: ClaimViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.fetchClaim()
    }

    override fun onStart() {
        super.onStart()
        sfl_container.setShimmer(Shimmer.AlphaHighlightBuilder()
            .setBaseAlpha(0.8f)
            .setDuration(3_000)
            .build())
        sfl_container.startShimmer()

        viewModel.claim.observe(this, Observer {
            when (it) {
                is DataEntity.ERROR -> {
                }
                is DataEntity.LOADING -> {
                    sfl_container.visible()
                }
                is DataEntity.SUCCESS -> {
                    sfl_container.stopShimmer()
                    sfl_container.gone()
                    claims_container.visible()
                    claim_card_layout.claim_card_place_holder_tv_claim_number.text = it.data?.claimNumber

                }
            }
        })
    }
}

fun View.gone(){this.visibility = View.GONE}
fun View.visible(){this.visibility = View.VISIBLE}
