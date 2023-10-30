package com.nane.join.presentation.view

import androidx.fragment.app.viewModels
import com.nane.base.view.BaseBindFragment
import com.nane.join.R
import com.nane.join.databinding.JoinEmailAuthFragmentBinding
import com.nane.join.presentation.viewmodel.JoinViewModel

/**
 * Created by haul on 10/29/23
 */
class JoinEmailAuthFragment : BaseBindFragment<JoinEmailAuthFragmentBinding, JoinViewModel>(R.layout.join_email_auth_fragment) {

    override fun createViewModel() = viewModels<JoinViewModel>().value

    override fun initFragment(dataBinding: JoinEmailAuthFragmentBinding, viewModel: JoinViewModel) {

    }
}