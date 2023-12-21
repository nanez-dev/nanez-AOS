package org.techtown.nanez

import androidx.fragment.app.viewModels
import com.nane.base.view.BaseBindFragment
import com.nane.base.viewmodel.BaseViewModel
import com.nane.login.presentation.view.LoginActivity
import org.techtown.nanez.databinding.StorageFragmentBinding
import org.techtown.nanez.utils.session.SessionManager

class StorageFragment : BaseBindFragment<StorageFragmentBinding, BaseViewModel>(R.layout.storage_fragment) {

    override fun createViewModel() = viewModels<BaseViewModel>().value

    override fun initFragment(dataBinding: StorageFragmentBinding, viewModel: BaseViewModel) {

        dataBinding.btnMoveLogin.setOnClickListener {
            activity?.let {
                it.startActivity(LoginActivity.createIntent(it))
            }
        }
        
        dataBinding.btnMoveLogin.text = "로그인 이동 \n 현재 로그인 ${SessionManager.instance.isLoginCheck()}"
    }

}