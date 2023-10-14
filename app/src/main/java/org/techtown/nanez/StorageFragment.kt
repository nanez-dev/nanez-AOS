package org.techtown.nanez

import androidx.fragment.app.viewModels
import com.nane.base.view.BaseBindFragment
import com.nane.base.viewmodel.BaseViewModel
import com.nane.login.presentation.view.LoginActivity
import org.techtown.nanez.databinding.StorageFragmentBinding

class StorageFragment : BaseBindFragment<StorageFragmentBinding, BaseViewModel>(R.layout.storage_fragment) {

    override fun createViewModel() = viewModels<BaseViewModel>().value

    override fun initFragment(dataBinding: StorageFragmentBinding, viewModel: BaseViewModel) {

        dataBinding.btnMoveLogin.setOnClickListener {
            activity?.let {
                it.startActivity(LoginActivity.createIntent(it))
            }
        }
    }

}