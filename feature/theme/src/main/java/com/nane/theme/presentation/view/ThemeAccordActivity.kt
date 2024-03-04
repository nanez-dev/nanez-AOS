package com.nane.theme.presentation.view

import android.content.Context
import android.content.Intent
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.nane.base.view.BaseBindActivity
import com.nane.base.view.BaseBindFragment
import com.nane.theme.R
import com.nane.theme.databinding.ThemeAccordActivityBinding
import com.nane.theme.presentation.data.AccordViewType
import com.nane.theme.presentation.view.adapter.AccordAdapter
import com.nane.theme.presentation.viewmodel.ThemeAccordViewModel
import dagger.hilt.android.AndroidEntryPoint
import org.techtown.nanez.utils.util.ResUtils
import org.techtown.nanez.utils.util.addFragment
import org.techtown.nanez.utils.util.toDp

@AndroidEntryPoint
class ThemeAccordActivity : BaseBindActivity<ThemeAccordActivityBinding, ThemeAccordViewModel>(R.layout.theme_accord_activity) {
    private val accordItemWidth = 64.toDp()
    private var spanCount = 5

    private var isDirectlyNavigatedToDetail = false

    override fun createViewModel(): ThemeAccordViewModel = viewModels<ThemeAccordViewModel>().value

    // 상세 화면으로 바로 이동한 경우는 뒤로가기 시 액티비티 종료
    override fun onActionBackPressed() {
        supportFragmentManager.also {
            val fragment = it.findFragmentById(dataBinding?.container?.id ?: 0) as? BaseBindFragment<*, *>
            if (isDirectlyNavigatedToDetail || fragment == null) {
                finish()
            } else {
                it.popBackStack()
                it.beginTransaction().remove(fragment).commit()
                setDetailFragmentVisibility(false)
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

            with(rvAccordItems) {
                adapter ?: AccordAdapter().apply {
                    setOnAccordItemClickListener(
                        object: AccordAdapter.AccordItemClickListener {
                            override fun onPopularAccordItemClick(itemId: Int) {
                                addFragment(dataBinding.container, tag = TAG_FRAGMENT, arguments = ThemeAccordDetailFragment.createArgument(itemId)) {
                                    ThemeAccordDetailFragment()
                                }
                                setDetailFragmentVisibility(true)
                            }

                            override fun onAllAccordItemClick(itemId: Int) {
                                addFragment(dataBinding.container, tag = TAG_FRAGMENT, arguments = ThemeAccordDetailFragment.createArgument(itemId)) {
                                    ThemeAccordDetailFragment()
                                }
                                setDetailFragmentVisibility(true)
                            }
                        }
                    )
                    adapter = this
                }
                layoutManager ?: GridLayoutManager(context, spanCount).apply {
                    spanSizeLookup = object: GridLayoutManager.SpanSizeLookup() {
                        override fun getSpanSize(position: Int): Int {
                            return when ((adapter as AccordAdapter).getItemViewType(position)) {
                                AccordViewType.ALL_ACCORD_ITEM_TYPE -> 1
                                else -> spanCount
                            }
                        }
                    }

                    layoutManager = this
                }
            }
        }

        viewModel.accordItemViewDataList.observe(this) {
            (dataBinding.rvAccordItems.adapter as? AccordAdapter)?.setItemList(it)
        }

        val accordId = intent?.getIntExtra(ThemeAccordDetailFragment.ACCORD_ID, -1) ?: -1
        if (accordId > 0) {
            isDirectlyNavigatedToDetail = true
            setDetailFragmentVisibility(true)
            addFragment(dataBinding.container, tag = TAG_FRAGMENT, arguments = ThemeAccordDetailFragment.createArgument(accordId)) {
                ThemeAccordDetailFragment()
            }
        } else {
            isDirectlyNavigatedToDetail = false
            setDetailFragmentVisibility(false)
            viewModel.getAccordViewData()
        }
    }

    private fun setDetailFragmentVisibility(isVisible: Boolean) {
        if (isVisible) {
            dataBinding?.container?.visibility = View.VISIBLE
            dataBinding?.rvAccordItems?.visibility = View.GONE
        } else {
            dataBinding?.container?.visibility = View.GONE
            dataBinding?.rvAccordItems?.visibility = View.VISIBLE
        }
    }

    companion object {
        private const val TAG_FRAGMENT = "ThemeAccordDetailFragment"

        fun createIntent(context: Context, accordId: Int): Intent {
            return Intent(context, ThemeAccordActivity::class.java).apply {
                addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
                putExtra(ThemeAccordDetailFragment.ACCORD_ID, accordId)
            }
        }
    }
}