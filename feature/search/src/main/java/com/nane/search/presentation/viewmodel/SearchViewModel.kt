package com.nane.search.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.nane.base.data.DomainResult
import com.nane.base.viewmodel.BaseViewModel
import com.nane.search.domain.usecase.PerfumesUsecase
import com.nane.search.presentation.data.SearchViewData
import com.nane.search.presentation.mapper.PerfumesMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.techtown.nanez.utils.util.post
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val perfumesUsecase: PerfumesUsecase,
    private val mapper: PerfumesMapper
): BaseViewModel() {

    private var loadPosition: Int = 1
    private var isLastItemLoaded: Boolean = false

    var searchWord: String = ""
        private set

    private val _recommendedSearchWords by lazy { MutableLiveData<SearchViewData.RecommendationListViewType>() }
    val recommendedSearchWords: LiveData<SearchViewData.RecommendationListViewType>
        get() = _recommendedSearchWords

    private val _searchResult by lazy { MutableLiveData<SearchViewData.SearchPerfumeListViewType>() }
    val searchResult: LiveData<SearchViewData.SearchPerfumeListViewType>
        get() = _searchResult

    fun searchWith(word: String) {
        if (word == searchWord) return

        viewModelScope.launch {
            searchWord = word
            loadPosition = 1
            perfumesUsecase.getPerfumes(query = searchWord, loadPosition = loadPosition, loadSize = ITEM_LOAD_SIZE).collect { result ->
                when (result) {
                    is DomainResult.Success -> {
                        val loadedItemsSize = result.data.perfumes?.size ?: 0
                        val viewData = mapper.toViewData(result.data)
                        _searchResult.post(
                            viewData.first { it is SearchViewData.SearchPerfumeListViewType } as SearchViewData.SearchPerfumeListViewType
                        )

                        // ITEM_LOAD_SIZE보다 적은 개수의 아이템 로드 시 마지막 아이템 로드된 것으로 간주
                        if (loadedItemsSize < ITEM_LOAD_SIZE) {
                            isLastItemLoaded = true
                        }
                        loadPosition += loadedItemsSize
                    }
                    is DomainResult.Failed -> {

                    }
                    is DomainResult.Error -> {

                    }
                }
            }
        }
    }

    fun getMorePerfumes() {
        if (isLastItemLoaded) return

        viewModelScope.launch {
            perfumesUsecase.getPerfumes(
                query = searchWord,
                loadPosition = loadPosition,
                loadSize = ITEM_LOAD_SIZE
            ).collect { result ->
                when (result) {
                    is DomainResult.Success -> {
                        val loadedItemsSize = result.data.perfumes?.size ?: 0
                        val viewData = mapper.toViewData(result.data).first { it is SearchViewData.SearchPerfumeListViewType } as SearchViewData.SearchPerfumeListViewType

                        val addedViewData = SearchViewData.SearchPerfumeListViewType(
                            list = mutableListOf<SearchViewData.SearchPerfumeListViewType.SearchPerfumeViewData>().apply {
                                addAll(_searchResult.value?.list ?: emptyList())
                                addAll(viewData.list)
                            }
                        )
                        _searchResult.post(addedViewData)

                        // ITEM_LOAD_SIZE보다 적은 개수의 아이템 로드 시 마지막 아이템 로드된 것으로 간주
                        if (loadedItemsSize < ITEM_LOAD_SIZE) {
                            isLastItemLoaded = true
                        }
                        loadPosition += loadedItemsSize
                    }
                    is DomainResult.Failed -> {

                    }
                    is DomainResult.Error -> {

                    }
                }
            }
        }
    }

    companion object {
        private const val ITEM_LOAD_SIZE = 10
    }
}