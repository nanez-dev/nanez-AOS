package com.nane.detail.presentation.view

import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.nane.base.view.BaseBindFragment
import com.nane.detail.R
import com.nane.detail.databinding.PerfumeDetailFragmentBinding
import com.nane.detail.presentation.data.PerfumeDetailEvent
import com.nane.detail.presentation.viewmodel.PerfumeDetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import org.techtown.nanez.utils.util.eventObserve

/**
 * Created by haul on 3/10/24
 */
@AndroidEntryPoint
class PerfumeDetailFragment : BaseBindFragment<PerfumeDetailFragmentBinding, PerfumeDetailViewModel>(R.layout.perfume_detail_fragment) {

    override fun createViewModel() = viewModels<PerfumeDetailViewModel>().value

    override fun initFragment(dataBinding: PerfumeDetailFragmentBinding, viewModel: PerfumeDetailViewModel) {
        dataBinding.run {
            with(recyclerView) {
                adapter

                layoutManager ?: LinearLayoutManager(context).apply {
                    layoutManager = this
                }

                if (itemDecorationCount == 0) {

                }
            }
        }

        viewModel.eventData.eventObserve(viewLifecycleOwner) { event ->
            when (event) {
                is PerfumeDetailEvent.Finish -> {
                    activity?.finish()
                }
                is PerfumeDetailEvent.InitView -> {

                }
            }
        }
    }
}