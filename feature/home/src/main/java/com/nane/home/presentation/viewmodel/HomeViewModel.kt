package com.nane.home.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.nane.base.data.DomainResult
import com.nane.base.viewmodel.BaseViewModel
import com.nane.home.domain.usecase.HomeInfoUseCase
import com.nane.home.presentation.data.HomeViewData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.techtown.nanez.utils.util.post
import javax.inject.Inject

/**
 * Created by iseungjun on 2023/08/19
 */
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeInfoUseCase: HomeInfoUseCase,
): BaseViewModel() {

    private val _viewDataList by lazy { MutableLiveData<List<HomeViewData>>() }
    val viewDataList: LiveData<List<HomeViewData>> = _viewDataList


    fun getMainData() {
        viewModelScope.launch {
            showLoading(true)
            homeInfoUseCase.getHomeInfo().collect { result ->
                when (result) {
                    is DomainResult.Success -> {
                        _viewDataList.post(result.data)
                        showLoading(false)
                    }
                    is DomainResult.Failed -> {
                        showLoading(false)
                        showErrorToast(result.msg)
                    }
                    is DomainResult.Error -> {
                        showLoading(false)
                        showErrorToast()
                    }
                }
            }

        }
    }
}