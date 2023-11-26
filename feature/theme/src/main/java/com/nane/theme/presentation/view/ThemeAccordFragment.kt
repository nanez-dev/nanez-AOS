package com.nane.theme.presentation.view

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.nane.base.view.BaseBindFragment
import com.nane.theme.R
import com.nane.theme.databinding.ThemeAccordFragmentBinding
import com.nane.theme.presentation.view.adapter.AllAccordsAdapter
import com.nane.theme.presentation.view.adapter.PopularAccordsAdapter
import com.nane.theme.presentation.view.adapter.decoration.PopularAccordItemDecoration
import com.nane.theme.presentation.viewmodel.ThemeAccordViewModel
import dagger.hilt.android.AndroidEntryPoint
import org.techtown.nanez.utils.util.ResUtils
import org.techtown.nanez.utils.util.toDp

@AndroidEntryPoint
class ThemeAccordFragment : BaseBindFragment<ThemeAccordFragmentBinding, ThemeAccordViewModel>(R.layout.theme_accord_fragment) {

    private val accordDetailFragment = ThemeAccordDetailFragment()

    private val accordItemWidth = 64.toDp()
    private var spanCount = 5

    override fun createViewModel(): ThemeAccordViewModel = viewModels<ThemeAccordViewModel>().value

    override fun initFragment(dataBinding: ThemeAccordFragmentBinding, viewModel: ThemeAccordViewModel) {
        spanCount = ResUtils.displayMetrics.widthPixels / accordItemWidth - 1

        dataBinding.apply {
            with(actionBar) {
                setTitle(getString(com.nane.base.R.string.label_theme_accord_title))
                setUseBackBtn {
//                     parentFragmentManager
//                         .beginTransaction()
//                         .replace()
//                         .commit()
                }
            }

            with(rvPopularAccords) {
                adapter ?: PopularAccordsAdapter().apply { adapter = this }
                layoutManager ?: LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false).apply { layoutManager = this }
                if (itemDecorationCount == 0) addItemDecoration(PopularAccordItemDecoration())
            }

            with(rvAllAccords) {
                adapter ?: AllAccordsAdapter().apply { adapter = this }
                if (itemDecorationCount == 0) layoutManager ?: GridLayoutManager(context, spanCount).apply { layoutManager = this }
                (adapter as AllAccordsAdapter).setOnItemClickListener(
                    object: AllAccordsAdapter.ItemClickListener {
                        override fun onItemClick(idx: Int) {
                            val args = Bundle()
                            args.putInt(ThemeAccordDetailFragment.ACCORD_ID, idx)
                            accordDetailFragment.arguments = args
                            parentFragmentManager
                                .beginTransaction()
                                .replace(android.R.id.content, accordDetailFragment)
                                .commit()
                        }
                    }
                )
            }
        }
        
        viewModel.popularAccordItemViewDataList.observe(viewLifecycleOwner) {
            (dataBinding.rvPopularAccords.adapter as? PopularAccordsAdapter)?.setItemList(it)
        }

        viewModel.allAccordItemViewDataList.observe(viewLifecycleOwner) {
            (dataBinding.rvAllAccords.adapter as? AllAccordsAdapter)?.setItemList(it)
        }

        viewModel.getAccordViewData()
    }
}