package com.nane.theme.presentation.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.nane.base.view.BaseBindActivity
import com.nane.base.view.BaseBindFragment
import com.nane.theme.R
import com.nane.theme.databinding.ThemeAccordActivityBinding
import com.nane.theme.presentation.view.adapter.AllAccordsAdapter
import com.nane.theme.presentation.view.adapter.PopularAccordsAdapter
import com.nane.theme.presentation.view.adapter.decoration.PopularAccordItemDecoration
import com.nane.theme.presentation.viewmodel.ThemeAccordViewModel
import dagger.hilt.android.AndroidEntryPoint
import org.techtown.nanez.utils.util.ResUtils
import org.techtown.nanez.utils.util.addFragment
import org.techtown.nanez.utils.util.toDp

@AndroidEntryPoint
class ThemeAccordActivity : BaseBindActivity<ThemeAccordActivityBinding, ThemeAccordViewModel>(R.layout.theme_accord_activity) {
    private val accordItemWidth = 64.toDp()
    private var spanCount = 5

    // 상세로 바로 이동한 경우는 뒤로가기시 전체 종료 시킴
    private var isMoveToDetail = false

    override fun createViewModel(): ThemeAccordViewModel = viewModels<ThemeAccordViewModel>().value

    override fun onActionBackPressed() {
        supportFragmentManager.also {
            val fragment = it.findFragmentById(dataBinding?.container?.id ?: 0) as? BaseBindFragment<*, *>
            if (isMoveToDetail || fragment == null) {
                finish()
            } else {
                it.popBackStack()
                it.beginTransaction().remove(fragment).commit()
                visibleDetailView(false)
            }
        }
    }

    override fun initActivity(dataBinding: ThemeAccordActivityBinding, viewModel: ThemeAccordViewModel) {
        spanCount = ResUtils.displayMetrics.widthPixels / accordItemWidth - 1

        dataBinding.apply {
            with(actionBar) {
                setTitle(getString(com.nane.base.R.string.label_theme_accord_title))
                setUseBackBtn { onActionBackPressed() }
            }

            with(rvPopularAccords) {
                adapter ?: PopularAccordsAdapter().apply { adapter = this }
                layoutManager ?: LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false).apply { layoutManager = this }
                if (itemDecorationCount == 0) addItemDecoration(PopularAccordItemDecoration())
                (adapter as PopularAccordsAdapter).setOnItemClickListener(
                    object: PopularAccordsAdapter.ItemClickListener {
                        override fun onItemClick(idx: Int) {
                            addFragment(dataBinding.container, tag = "ThemeAccordDetailFragment", arguments = ThemeAccordDetailFragment.createArgument(idx)) {
                                ThemeAccordDetailFragment()
                            }
                            visibleDetailView(true)
                        }
                    }
                )
            }

            with(rvAllAccords) {
                adapter ?: AllAccordsAdapter().apply { adapter = this }
                layoutManager ?: GridLayoutManager(context, spanCount).apply { layoutManager = this }
                (adapter as AllAccordsAdapter).setOnItemClickListener(
                    object: AllAccordsAdapter.ItemClickListener {
                        override fun onItemClick(idx: Int) {
                            addFragment(dataBinding.container, tag = "ThemeAccordDetailFragment", arguments = ThemeAccordDetailFragment.createArgument(idx)) {
                                ThemeAccordDetailFragment()
                            }
                            visibleDetailView(true)
                        }
                    }
                )
            }
        }

        viewModel.popularAccordItemViewDataList.observe(this) {
            (dataBinding.rvPopularAccords.adapter as? PopularAccordsAdapter)?.setItemList(it)
        }

        viewModel.allAccordItemViewDataList.observe(this) {
            (dataBinding.rvAllAccords.adapter as? AllAccordsAdapter)?.setItemList(it)
        }

        val accordId = intent?.getIntExtra(ThemeAccordDetailFragment.ACCORD_ID, -1) ?: -1
        if (accordId > 0) {
            isMoveToDetail = true
            visibleDetailView(true)
            addFragment(dataBinding.container, tag = "ThemeAccordDetailFragment", arguments = ThemeAccordDetailFragment.createArgument(accordId)) {
                ThemeAccordDetailFragment()
            }
        } else {
            isMoveToDetail = false
            visibleDetailView(false)
            viewModel.getAccordViewData()
        }
    }


    private fun visibleDetailView(isVisible: Boolean) {
        if (isVisible) {
            dataBinding?.container?.visibility = View.VISIBLE
            dataBinding?.vgMain?.visibility = View.GONE
        } else {
            dataBinding?.container?.visibility = View.GONE
            dataBinding?.vgMain?.visibility = View.VISIBLE
        }
    }

    companion object {
        fun createIntent(context: Context, accordId: Int): Intent {
            return Intent(context, ThemeAccordActivity::class.java).apply {
                addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
                putExtra(ThemeAccordDetailFragment.ACCORD_ID, accordId)
            }
        }
    }
}