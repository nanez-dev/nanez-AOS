package org.techtown.nanez

import androidx.fragment.app.viewModels
import com.nane.base.view.BaseBindFragment
import com.nane.base.viewmodel.BaseViewModel
import org.techtown.nanez.databinding.LoginProfileFragmentBinding
import org.techtown.nanez.databinding.NoLoginProfileFragmentBinding
import org.techtown.nanez.databinding.ProfileFragmentBinding
import org.techtown.nanez.utils.session.SessionManager

class ProfileFragment : BaseBindFragment<ProfileFragmentBinding, BaseViewModel>(
    R.layout.profile_fragment
) {

    override fun createViewModel() = viewModels<BaseViewModel>().value

    override fun initFragment(dataBinding: ProfileFragmentBinding, viewModel: BaseViewModel) {
        val sessionManager = SessionManager.instance

        if (sessionManager.isLoginCheck()) {
            val loggedInBinding = LoginProfileFragmentBinding.bind(dataBinding.root)
            dataBinding.constraintLayout.removeAllViews()
            dataBinding.constraintLayout.addView(loggedInBinding.root)

            loggedInBinding.txtMoreInfo.text = sessionManager.getUserEmail() ?: ""

        } else {
            val loggedOutBinding = NoLoginProfileFragmentBinding.bind(dataBinding.root)
            dataBinding.constraintLayout.removeAllViews()
            dataBinding.constraintLayout.addView(loggedOutBinding.root)

        }
    }
}
