package com.nane.setting.presentation.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.nane.base.view.BaseBindActivity
import com.nane.base.view.BaseBindFragment
import com.nane.setting.R
import com.nane.setting.databinding.RegistrationActivityBinding
import com.nane.setting.presentation.data.RegistrationEvent
import com.nane.setting.presentation.viewmodel.RegistrationViewModel
import dagger.hilt.android.AndroidEntryPoint
import org.techtown.nanez.utils.util.ResUtils
import org.techtown.nanez.utils.util.addFragment
import org.techtown.nanez.utils.util.eventObserve

@AndroidEntryPoint
class RegistrationActivity :
    BaseBindActivity<RegistrationActivityBinding, RegistrationViewModel>(R.layout.registration_activity) {

    private var index = INDEX_BRAND_NAME

    override fun createViewModel(): RegistrationViewModel = viewModels<RegistrationViewModel>().value

    override fun onActionBackPressed() {
        if (index == INDEX_REQUEST_COMPLETION) {
            finish()
        }
        supportFragmentManager.also {
            val fragment = it.findFragmentById(dataBinding?.registrationContainer?.id ?: 0) as? BaseBindFragment<*, *>
            val isFragmentInterceptor = fragment?.onBackPressedInterceptor() ?: false
            if (!isFragmentInterceptor) {
                if (it.backStackEntryCount > 1) {
                    viewModel?.postPreStep()
                } else {
                    finish()
                }
            }
        }
    }

    override fun initActivity(dataBinding: RegistrationActivityBinding, viewModel: RegistrationViewModel) {
        dataBinding.progressView.setProgress(50, true)
        addFragment(
            container = dataBinding.registrationContainer,
            tag = "BrandNameFragment",
            arguments = Bundle().apply { putString(REQUEST_ITEM, REQUEST_BRAND) },
            isBackStackEnabled = true
        ) {
            RegistrationFragment()
        }

        with(dataBinding) {
            actionBar.setTitle(ResUtils.instance.getString(com.nane.base.R.string.label_profile_product_registration_request))
            actionBar.setUseBackBtn {
                finish()
            }
            btnGoMain.setOnClickListener {
                finish()
            }
        }

        viewModel.eventData.eventObserve(this@RegistrationActivity) { event ->
            when (event) {
                is RegistrationEvent.MoveNextStep -> {
                    index++

                    when (index) {
                        INDEX_PERFUME_NAME -> {
                            val bundle = Bundle().apply {
                                putString(REQUEST_ITEM, REQUEST_PERFUME)
                            }
                            dataBinding.progressView.setProgress(100, true)
                            addFragment(dataBinding.registrationContainer, tag = "PerfumeNameFragment", arguments = bundle, isBackStackEnabled = true) {
                                RegistrationFragment()
                            }
                        }
                        INDEX_REQUEST_COMPLETION -> {
                            with(dataBinding) {
                                progressView.visibility = View.GONE
                                registrationContainer.visibility = View.GONE
                                vgRequestCompletion.visibility = View.VISIBLE
                                btnGoMain.visibility = View.VISIBLE
                            }

                            if (supportFragmentManager.backStackEntryCount > 1) {
                                supportFragmentManager.popBackStack()
                            }
                        }
                    }
                }
                is RegistrationEvent.MovePreStep -> {
                    index--

                    when (index) {
                        INDEX_BRAND_NAME -> {
                            dataBinding.progressView.setProgress(50, true)
                            supportFragmentManager.popBackStack()
                        }
                    }
                }
            }
        }
    }

    companion object {

        private const val INDEX_BRAND_NAME = 1
        private const val INDEX_PERFUME_NAME = 2
        private const val INDEX_REQUEST_COMPLETION = 3

        const val REQUEST_ITEM = "request_item"
        const val REQUEST_BRAND = "request_brand"
        const val REQUEST_PERFUME = "request_perfume"

        fun createIntent(context: Context): Intent {
            return Intent(context, RegistrationActivity::class.java).apply {
                addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP or Intent.FLAG_ACTIVITY_CLEAR_TOP)
            }
        }
    }
}