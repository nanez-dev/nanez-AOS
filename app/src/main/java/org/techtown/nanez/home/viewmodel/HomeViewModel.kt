package org.techtown.nanez.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.techtown.nanez.base.BaseViewModel
import org.techtown.nanez.domain.data.DomainResult
import org.techtown.nanez.domain.usecase.HomeInfoUseCase
import org.techtown.nanez.home.data.HomeViewData
import org.techtown.nanez.home.mapper.HomeViewDataMapper
import org.techtown.nanez.utils.util.post
import javax.inject.Inject

/**
 * Created by iseungjun on 2023/08/19
 */
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeInfoUseCase: HomeInfoUseCase,
    private val mapper: HomeViewDataMapper,
): BaseViewModel() {

    private val _viewDataList by lazy { MutableLiveData<List<HomeViewData>>() }
    val viewDataList: LiveData<List<HomeViewData>> = _viewDataList


    fun getMainData() {
        viewModelScope.launch {
            homeInfoUseCase.getHomeInfo().collect { result ->
                when (result) {
                    is DomainResult.Success -> {
                        _viewDataList.post(mapper.toViewData(result.data))
                    }
                    is DomainResult.Failed -> {

                    }
                    is DomainResult.Error -> {

                    }
                }
            }
        }
    }
}