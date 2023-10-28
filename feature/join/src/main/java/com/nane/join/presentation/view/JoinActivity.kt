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
    override fun createViewModel() = viewModels<JoinActViewModel>().value

    override fun initActivity(dataBinding: JoinActivityBinding, viewModel: JoinActViewModel) {
        addFragment(dataBinding.container, tag = "JoinAgreementFragment") {
            JoinAgreementFragment()
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
                    viewModel?.postPreStep()
                    onBackPressedDispatcher.onBackPressed()
                } else {
                    finish()
                }
            }
        }
    }


    companion object {

        fun createIntent(context: Context): Intent {
            return Intent(context, JoinActivity::class.java).apply {
                addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP or Intent.FLAG_ACTIVITY_CLEAR_TOP)
            }
        }
    }
}