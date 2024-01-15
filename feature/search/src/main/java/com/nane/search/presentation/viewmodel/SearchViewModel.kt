package com.nane.search.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.nane.base.data.DomainResult
import com.nane.base.viewmodel.BaseViewModel
import com.nane.search.domain.usecase.PerfumesUsecase
import com.nane.search.presentation.data.SearchResultViewData
import com.nane.search.presentation.mapper.PerfumesMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.techtown.nanez.utils.util.post
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val perfumesUsecase: PerfumesUsecase,
    private val mapper: PerfumesMapper
) : BaseViewModel() {

    private var loadPosition: Int = 1
    private var isLastItemLoaded: Boolean = false

    var searchWord: String = ""
        private set

    private val _isLoading by lazy { MutableLiveData<Boolean>(false) }
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    private val _searchResults by lazy { MutableLiveData<List<SearchResultViewData>>() }
    val searchResults: LiveData<List<SearchResultViewData>>
        get() = _searchResults

    private val recommendedSearchWords by lazy { SearchResultViewData.RecommendedSearchWordListItemViewData(listOf()) }
    private val perfumes by lazy { mutableListOf<SearchResultViewData.SearchPerfumeViewData>() }

    fun searchWith(word: String) {
        if (word == searchWord) return

        viewModelScope.launch {
            _isLoading.post(true)
            searchWord = word
            loadPosition = 1
            perfumesUsecase.getPerfumes(
                query = searchWord,
                loadPosition = loadPosition,
                loadSize = ITEM_LOAD_SIZE
            ).collect { result ->
                when (result) {
                    is DomainResult.Success -> {
                        val loadedItemsSize = result.data.perfumes?.size ?: 0
                        perfumes.clear()
                        perfumes.addAll(mapper.toViewData(result.data))

                        val resultViewData = mutableListOf<SearchResultViewData>().apply {
                            add(recommendedSearchWords)
                            addAll(perfumes)
                        }
                        _searchResults.post(resultViewData)

                        // ITEM_LOAD_SIZE보다 적은 개수의 아이템 로드 시 마지막 아이템 로드된 것으로 간주
                        if (loadedItemsSize < ITEM_LOAD_SIZE) {
                            isLastItemLoaded = true
                        }
                        loadPosition += loadedItemsSize
                        _isLoading.post(false)
                    }

                    is DomainResult.Failed -> {
                        _isLoading.post(false)
                    }

                    is DomainResult.Error -> {
                        _isLoading.post(false)
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
                        val viewData = mapper.toViewData(result.data)

                        perfumes.addAll(viewData)

                        val resultViewData = mutableListOf<SearchResultViewData>().apply {
                            add(recommendedSearchWords)
                            addAll(mapper.toViewData(result.data))
                        }
                        _searchResults.post(resultViewData)

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

    init {
        // 검색어 추천 기능 하드코딩
        val recommendedWordList = listOf("이달의 향수", "성년의 날", "나네", "할인 향수")
        recommendedSearchWords.wordList = recommendedWordList
    }

    companion object {
        private const val ITEM_LOAD_SIZE = 10
    }
}