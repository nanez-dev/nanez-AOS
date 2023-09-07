package org.techtown.nanez

import androidx.fragment.app.viewModels
import com.nane.base.view.BaseBindFragment
import com.nane.base.viewmodel.BaseViewModel
import org.techtown.nanez.databinding.StorageFragmentBinding

class StorageFragment : BaseBindFragment<StorageFragmentBinding, BaseViewModel>(R.layout.storage_fragment) {

    override fun createViewModel() = viewModels<BaseViewModel>().value

    override fun initFragment(dataBinding: StorageFragmentBinding, viewModel: BaseViewModel) {

    }

}