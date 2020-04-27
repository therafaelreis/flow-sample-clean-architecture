package com.therafaelreis.flowsample.presentation.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.therafaelreis.flowsample.R
import com.therafaelreis.flowsample.presentation.model.DataEntity
import com.therafaelreis.flowsample.presentation.viewmodel.ClaimViewModel
import kotlinx.android.synthetic.main.activity_main.*
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
        viewModel.claim.observe(this, Observer {
            when (it) {
                is DataEntity.ERROR -> {
                }
                is DataEntity.LOADING -> {
                    empty_loading_claims.visible()
                    tv_claim_number.gone()
                }
                is DataEntity.SUCCESS -> {
                    empty_loading_claims.gone()
                    tv_claim_number.visible()
                    tv_claim_number.text = "Claims number: ${it.data?.claimNumber}"

                }
            }
        })
    }
}

fun View.gone(){this.visibility = View.GONE}
fun View.visible(){this.visibility = View.VISIBLE}
