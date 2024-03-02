package com.nane.search.presentation.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.nane.search.databinding.SearchRecommendedSearchWordItemViewBinding
import com.nane.search.presentation.data.SearchResultViewData
import com.nane.theme.BR

class RecommendedSearchWordAdapter: RecyclerView.Adapter<RecommendedSearchWordAdapter.RecommendedWordViewHolder>() {

    private var itemList: List<String> = emptyList()

    fun setItemList(item: List<String>) {
        itemList = item
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecommendedWordViewHolder {
        return RecommendedWordViewHolder(SearchRecommendedSearchWordItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int = itemList.size

    override fun onBindViewHolder(holder: RecommendedWordViewHolder, position: Int) {
        holder.onBind(itemList.getOrNull(position))
    }

    inner class RecommendedWordViewHolder(private val binding: SearchRecommendedSearchWordItemViewBinding): ViewHolder(binding.root) {
        fun onBind(data: String?) {
            data ?: return

            binding.setVariable(BR.viewData, data)
            binding.executePendingBindings()

            binding.txtRecommendedSearchWord.text = data
        }

        init {
            binding.txtRecommendedSearchWord.apply {
                setOnClickListener {
                    onSearchWordClickListener?.onSearchWordClick(adapterPosition)
                }
                clipToOutline = true
            }
        }
    }

    private var onSearchWordClickListener: SearchResultAdapter.SearchResultClickListener? = null
    fun setOnItemClickListener(itemClickListener: SearchResultAdapter.SearchResultClickListener?) {
        onSearchWordClickListener = itemClickListener
    }
}