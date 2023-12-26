package com.nane.search.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.nane.base.viewmodel.BaseViewModel
import com.nane.search.presentation.data.SearchResultItemViewData
import kotlinx.coroutines.launch
import org.techtown.nanez.utils.util.post
import javax.inject.Inject

class SearchViewModel @Inject constructor(): BaseViewModel() {

    private val _searchWord = MutableLiveData<String>()
    val searchWord: LiveData<String>
        get() = _searchWord

    private val _recommendedSearchWords by lazy { MutableLiveData<List<SearchResultItemViewData>>() }
    val recommendedSearchWords: LiveData<List<SearchResultItemViewData>>
        get() = _recommendedSearchWords

    private val _searchResult by lazy { MutableLiveData<List<SearchResultItemViewData>>() }
    val searchResult: LiveData<List<SearchResultItemViewData>>
        get() = _searchResult

    fun searchWith(word: String) {
        _searchWord.post(word)

        viewModelScope.launch {
            // 검색 후 결과 받아오기
        }
    }
}