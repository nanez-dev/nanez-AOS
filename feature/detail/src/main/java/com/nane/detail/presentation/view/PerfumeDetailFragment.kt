package com.nane.detail.presentation.view

import android.graphics.Rect
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nane.base.view.BaseBindFragment
import com.nane.detail.R
import com.nane.detail.databinding.PerfumeDetailFragmentBinding
import com.nane.detail.presentation.data.PerfumeDetailEvent
import com.nane.detail.presentation.view.adapter.PerfumeDetailAdapter
import com.nane.detail.presentation.viewmodel.PerfumeDetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import org.techtown.nanez.utils.util.eventObserve
import org.techtown.nanez.utils.util.toDp

/**
 * Created by haul on 3/10/24
 */
@AndroidEntryPoint
class PerfumeDetailFragment : BaseBindFragment<PerfumeDetailFragmentBinding, PerfumeDetailViewModel>(R.layout.perfume_detail_fragment) {

    override fun createViewModel() = viewModels<PerfumeDetailViewModel>().value

    override fun initFragment(dataBinding: PerfumeDetailFragmentBinding, viewModel: PerfumeDetailViewModel) {
        dataBinding.run {
            with(recyclerView) {
                itemAnimator = null

                adapter ?: PerfumeDetailAdapter().apply {
                    adapter = this
                }

                layoutManager ?: LinearLayoutManager(context).apply {
                    layoutManager = this
                }

                if (itemDecorationCount == 0) {
                    addItemDecoration(object : RecyclerView.ItemDecoration() {
                        override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
                            super.getItemOffsets(outRect, view, parent, state)
                            outRect.bottom = 52.toDp()
                        }
                    })
                }
            }
        }

        viewModel.eventData.eventObserve(viewLifecycleOwner) { event ->
            when (event) {
                is PerfumeDetailEvent.Finish -> {
                    activity?.finish()
                }
                is PerfumeDetailEvent.InitView -> {
                    (dataBinding.recyclerView.adapter as? PerfumeDetailAdapter)?.setViewData(event.viewData)
                }
            }
        }

        val perfumeId = arguments?.getInt(PERFUME_ID) ?: -1

        if (perfumeId < 0) {
            return
        }

        viewModel.getPerfumeDetailInfo(perfumeId)
    }

    companion object {
        const val PERFUME_ID = "perfume_id"

        fun createArgument(perfumeId: Int): Bundle {
            return Bundle().apply {
                putInt(PERFUME_ID, perfumeId)
            }
        }
    }
}