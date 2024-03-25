package com.nane.storage.presentation.view

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.tabs.TabLayout
import com.nane.base.view.BaseBindFragment
import com.nane.base.viewmodel.BaseViewModel
import com.nane.storage.R
import com.nane.storage.databinding.StorageFragmentBinding
import com.nane.storage.presentation.data.StorageViewType
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StorageFragment : BaseBindFragment<StorageFragmentBinding, BaseViewModel>(R.layout.storage_fragment) {

    private val wishListFragment: WishListFragment by lazy { WishListFragment() }
    private val havingListFragment: HavingListFragment by lazy { HavingListFragment() }


    override fun createViewModel() = viewModels<BaseViewModel>().value

    override fun initFragment(dataBinding: StorageFragmentBinding, viewModel: BaseViewModel) {
        dataBinding.apply {
            actionBar.setTitle(getString(com.nane.base.R.string.label_storage))
            actionBar.setLineViewVisible(false)
            
            switchFragmentByPosition(StorageViewType.STORAGE_WISH_POSITION)

            with (dataBinding.tabLayout) {
                addTab(newTab().setText(getString(com.nane.base.R.string.label_profile_wish)))
                addTab(newTab().setText(getString(com.nane.base.R.string.label_profile_having)))

                addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                    override fun onTabSelected(tab: TabLayout.Tab?) {
                        tab?.position?.let { switchFragmentByPosition(it) }
                    }
                    override fun onTabUnselected(tab: TabLayout.Tab?) {}
                    override fun onTabReselected(tab: TabLayout.Tab?) {}
                })
            }
        }
    }

    private fun switchFragmentByPosition(position: Int) {
        val selectedFragment: Fragment = when (position) {
            StorageViewType.STORAGE_WISH_POSITION -> wishListFragment
            StorageViewType.STORAGE_HAVING_POSITION -> havingListFragment
            else -> Fragment()
        }

        childFragmentManager.beginTransaction().replace(R.id.container, selectedFragment).commit()
    }
}