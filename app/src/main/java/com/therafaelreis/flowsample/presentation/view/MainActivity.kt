package com.therafaelreis.flowsample.presentation.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.facebook.shimmer.Shimmer
import com.therafaelreis.flowsample.R
import com.therafaelreis.flowsample.presentation.model.DataEntity
import com.therafaelreis.flowsample.presentation.viewmodel.ClaimViewModel
import com.therafaelreis.flowsample.presentation.viewmodel.PolicyViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.claim_card.view.*
import kotlinx.android.synthetic.main.home_content.view.*
import kotlinx.android.synthetic.main.policy_card.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity() {

    private val claimViewModel: ClaimViewModel by viewModel()
    private val policyViewModel: PolicyViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        claimViewModel.fetchClaim()
        policyViewModel.fetchPolicy()
    }

    override fun onStart() {
        super.onStart()

        Glide.with(this)
            .load("https://pbs.twimg.com/profile_images/1232673264817836039/P9-mdRRg_400x400.jpg")
            .error(R.drawable.profile_place_holder)
            .transform(CenterCrop(), RoundedCorners(50))
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(iv_profile)

        home_layout.sfl_claim_container.setShimmer(Shimmer.AlphaHighlightBuilder().build())
        home_layout.sfl_policy_container.setShimmer(Shimmer.AlphaHighlightBuilder().build())

        home_layout.sfl_policy_container.startShimmer()
        home_layout.sfl_claim_container.startShimmer()

        claimViewModel.claim.observe(this, Observer {
            when (it) {
                is DataEntity.ERROR -> {
                }
                is DataEntity.LOADING -> {
                    home_layout.sfl_claim_container.visible()
                }
                is DataEntity.SUCCESS -> {
                    home_layout.sfl_claim_container.stopShimmer()
                    home_layout.sfl_claim_container.invisible()
                    home_layout.claims_container.visible()
                    home_layout.claim_card_layout.claim_card_place_holder_tv_claim_number.text =
                        it.data?.claimNumber

                }
            }
        })

        policyViewModel.policy.observe(this, Observer {
            when (it) {
                is DataEntity.ERROR -> {
                    home_layout.sfl_policy_container.stopShimmer()
                }
                is DataEntity.LOADING -> {
                    home_layout.sfl_policy_container.visible()
                }
                is DataEntity.SUCCESS -> {
                    home_layout.sfl_policy_container.stopShimmer()
                    home_layout.sfl_policy_container.invisible()

                    if (it.data?.policyNumber != null) {
                        it.data?.let { policy ->
                            home_layout.policy_container.visible()
                            home_layout.policy_layout_card.policy_card_tv_policy_number.text = policy.vehicle
                        }
                    } else {
                        home_layout.empty_policy_container.visible()
                    }

                }
            }
        })

    }
}

fun View.gone() {
    this.visibility = View.GONE
}

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

