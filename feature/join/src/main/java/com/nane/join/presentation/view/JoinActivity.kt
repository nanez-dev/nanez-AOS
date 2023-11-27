package com.nane.join.presentation.view

import android.content.Context
import android.content.Intent
import androidx.activity.viewModels
import com.nane.base.view.BaseBindActivity
import com.nane.base.view.BaseBindFragment
import com.nane.join.R
import com.nane.join.databinding.JoinActivityBinding
import com.nane.join.presentation.data.JoinActEventData
import com.nane.join.presentation.viewmodel.JoinActViewModel
import dagger.hilt.android.AndroidEntryPoint
import org.techtown.nanez.utils.util.addFragment
import org.techtown.nanez.utils.util.eventObserve

/**
 * Created by haul on 10/28/23
 */
@AndroidEntryPoint
class JoinActivity : BaseBindActivity<JoinActivityBinding, JoinActViewModel>(R.layout.join_activity) {

    private var index = INDEX_AGREE

    override fun createViewModel() = viewModels<JoinActViewModel>().value

    override fun initActivity(dataBinding: JoinActivityBinding, viewModel: JoinActViewModel) {
//        addFragment(dataBinding.container, tag = "JoinAgreementFragment") {
//            JoinAgreementFragment()
//        }

        addFragment(dataBinding.container, tag = "JoinEventCodeFragment") {
            JoinEventCodeFragment()
        }

        dataBinding.apply {
            actionBar.setTitle(getString(com.nane.base.R.string.label_join))
            actionBar.setUseBackBtn {
                finish()
            }
        }

        viewModel.eventData.eventObserve(this) { event ->
            when (event) {
                is JoinActEventData.MoveNextStep -> {
                    index++

                    when (index) {
                        INDEX_AGREE -> {
                            addFragment(dataBinding.container, tag = "JoinAgreementFragment") {
                                JoinAgreementFragment()
                            }
                        }
                        INDEX_AUTH -> {
                            addFragment(dataBinding.container, tag = "JoinEmailAuthFragment") {
                                JoinEmailAuthFragment()
                            }
                        }
                        INDEX_PASSWORD -> {
                            addFragment(dataBinding.container, tag = "JoinPasswordFragment") {
                                JoinPasswordFragment()
                            }
                        }
                        INDEX_NICKNAME -> {
                            addFragment(dataBinding.container, tag = "JoinNickNameFragment") {
                                JoinNickNameFragment()
                            }
                        }
                        INDEX_SELECT_ACCORD -> {
                            addFragment(dataBinding.container, tag = "JoinSelectAccordFragment") {
                                JoinSelectAccordFragment()
                            }
                        }
                        INDEX_EVENT_CODE -> {
                            addFragment(dataBinding.container, tag = "JoinEventCodeFragment") {
                                JoinEventCodeFragment()
                            }
                        }
                        else -> {}
                    }
                }
                is JoinActEventData.ChangeProgressView -> {
                    dataBinding.progressView.setProgress(event.progress, true)
                }
            }
        }
    }

    override fun onActionBackPressed() {
        supportFragmentManager.also {
            val fragment = it.findFragmentById(dataBinding?.container?.id ?: 0) as? BaseBindFragment<*, *>
            val isFragmentInterceptor = fragment?.onBackPressedInterceptor() ?: false
            if (!isFragmentInterceptor) { // fragment에서 interceptor하지 않았을때만 super.onBackPressed를 하도록 한다.
                if (it.backStackEntryCount > 1) {
                    index--
                    viewModel?.postPreStep()
                    onBackPressedDispatcher.onBackPressed()
                } else {
                    finish()
                }
            }
        }
    }


    companion object {

        private const val INDEX_AGREE = 1
        private const val INDEX_AUTH = 2
        private const val INDEX_PASSWORD = 3
        private const val INDEX_NICKNAME = 4
        private const val INDEX_SELECT_ACCORD = 5
        private const val INDEX_EVENT_CODE = 6

        fun createIntent(context: Context): Intent {
            return Intent(context, JoinActivity::class.java).apply {
                addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP or Intent.FLAG_ACTIVITY_CLEAR_TOP)
            }
        }
    }
}