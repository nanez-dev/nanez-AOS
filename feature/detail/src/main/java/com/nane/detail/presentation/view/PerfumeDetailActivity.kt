package com.nane.detail.presentation.view

import android.content.Context
import android.content.Intent
import androidx.activity.viewModels
import com.nane.base.view.BaseBindActivity
import com.nane.base.viewmodel.BaseViewModel
import com.nane.detail.R
import com.nane.detail.databinding.PerfumeDetailActivityBinding
import dagger.hilt.android.AndroidEntryPoint
import org.techtown.nanez.utils.util.addFragment

/**
 * Created by haul on 3/10/24
 */
@AndroidEntryPoint
class PerfumeDetailActivity : BaseBindActivity<PerfumeDetailActivityBinding, BaseViewModel>(R.layout.perfume_detail_activity) {


    override fun createViewModel() = viewModels<BaseViewModel>().value

    override fun initActivity(dataBinding: PerfumeDetailActivityBinding, viewModel: BaseViewModel) {
       dataBinding.run {
           with(actionBar) {
               setTitle(getString(com.nane.base.R.string.label_perfume_detail_info))
               setUseBackBtn { onActionBackPressed() }
           }

           val perfumeId = intent.extras?.getInt(PerfumeDetailFragment.PERFUME_ID) ?: -1

           if (perfumeId < 0) {
               return
           }

           addFragment(container, null, "PerfumeDetailFragment", arguments = PerfumeDetailFragment.createArgument(perfumeId)) {
                PerfumeDetailFragment()
           }
       }
    }

    override fun onActionBackPressed() {
        finish()
    }


    companion object {

        fun createIntent(context: Context, perfumeId: Int): Intent {
            return Intent(context, PerfumeDetailActivity::class.java).apply {
                addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
                putExtra(PerfumeDetailFragment.PERFUME_ID, perfumeId)
            }
        }
    }
}