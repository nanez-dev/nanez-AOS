package com.nane.join.presentation.view

import android.graphics.Rect
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nane.base.view.BaseBindFragment
import com.nane.join.R
import com.nane.join.databinding.JoinSelectAccordFragmentBinding
import com.nane.join.presentation.view.adapter.JoinSelectAccordAdapter
import com.nane.join.presentation.viewmodel.JoinActViewModel
import com.nane.join.presentation.viewmodel.JoinViewModel
import dagger.hilt.android.AndroidEntryPoint
import org.techtown.nanez.utils.util.toDp

/**
 * Created by haul on 11/20/23
 */
@AndroidEntryPoint
class JoinSelectAccordFragment : BaseBindFragment<JoinSelectAccordFragmentBinding, JoinViewModel>(R.layout.join_select_accord_fragment) {

    private val actViewModel: JoinActViewModel by activityViewModels()

    private var selectAccordId = -1

    override fun createViewModel() = viewModels<JoinViewModel>().value

    override fun initFragment(dataBinding: JoinSelectAccordFragmentBinding, viewModel: JoinViewModel) {
        dataBinding.apply {
            btnDoNext.setOnClickListener {
                actViewModel.updateAccordId(selectAccordId)
            }

            btnSkip.setOnClickListener {
                actViewModel.updateAccordId(-1)
            }


            with(recyclerView) {
                layoutManager ?: GridLayoutManager(context, 5).apply { layoutManager = this }
                adapter ?: JoinSelectAccordAdapter().apply {
                    userActionsListener = object : JoinSelectAccordAdapter.UserActionsListener {
                        override fun onSelectAccord(targetId: Int) {
                            dataBinding.btnDoNext.isEnabled = targetId >= 0
                            selectAccordId = targetId
                        }
                    }
                    adapter = this
                }
            }
        }


        viewModel.accordList.observe(viewLifecycleOwner) { list ->
            (dataBinding.recyclerView.adapter as? JoinSelectAccordAdapter)?.setDataList(list)
        }

        viewModel.getAllAccordList()
    }


}