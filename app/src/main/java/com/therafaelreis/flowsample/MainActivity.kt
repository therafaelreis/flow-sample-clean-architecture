package com.therafaelreis.flowsample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import com.therafaelreis.flowsample.presentation.model.DataEntity
import com.therafaelreis.flowsample.presentation.viewmodel.ClaimViewModel
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
                    //Error handling
                }
                is DataEntity.LOADING -> {
                    //Progress
                }
                is DataEntity.SUCCESS -> {
                    println("GOT HERE " + it.data)
                }
            }
        })
    }
}
