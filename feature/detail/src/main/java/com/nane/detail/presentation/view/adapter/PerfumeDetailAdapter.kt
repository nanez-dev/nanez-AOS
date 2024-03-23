package com.nane.detail.presentation.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nane.detail.databinding.PerfumeDetailAccordViewBinding
import com.nane.detail.databinding.PerfumeDetailBasicViewBinding
import com.nane.detail.databinding.PerfumeDetailNoteViewBinding
import com.nane.detail.presentation.data.PerfumeDetailViewData
import com.nane.detail.presentation.data.PerfumeDetailViewType
import com.nane.detail.presentation.viewmodel.PerfumeDetailViewModel

/**
 * Created by haul on 3/10/24
 */
class PerfumeDetailAdapter(
    private val vm: PerfumeDetailViewModel
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var viewData: PerfumeDetailViewData? = null

    fun setViewData(viewData: PerfumeDetailViewData) {
        this.viewData = viewData
        notifyItemRangeChanged(0, TOTAL_COUNT)
    }

    fun notifyWishStatus(isWish: Boolean) {
        viewData?.basicInfo?.isWish = isWish
        notifyItemChanged(0)
    }

    fun notifyHavingStatus(isHaving: Boolean) {
        viewData?.basicInfo?.isHaving = isHaving
        notifyItemChanged(0)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            PerfumeDetailViewType.TYPE_BASIC -> {
                PerfumeDetailBasicViewHolder(
                    PerfumeDetailBasicViewBinding.inflate(LayoutInflater.from(parent.context), parent, false),
                    vm
                )
            }
            PerfumeDetailViewType.TYPE_ACCORD -> {
                PerfumeDetailAccordViewHolder(
                    PerfumeDetailAccordViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                )
            }
            PerfumeDetailViewType.TYPE_NOTE -> {
                PerfumeDetailNoteViewHolder(
                    PerfumeDetailNoteViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                )
            }
            else -> {
                PerfumeEmptyViewHolder(View(parent.context))
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is PerfumeDetailBasicViewHolder -> {
                viewData?.basicInfo?.let {
                    holder.onBind(it)
                }
            }
            is PerfumeDetailAccordViewHolder -> {
                viewData?.accordInfo?.let {
                    if (it.isNotEmpty()) {
                        holder.onBind(it)
                    }
                }
            }
            is PerfumeDetailNoteViewHolder -> {
                viewData?.noteInfo?.let {
                    holder.onBind(it)
                }
            }
        }
    }

    override fun getItemCount() = TOTAL_COUNT

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            0 -> PerfumeDetailViewType.TYPE_BASIC
            1 -> PerfumeDetailViewType.TYPE_ACCORD
            2 -> PerfumeDetailViewType.TYPE_NOTE
            else -> -1
        }
    }

    companion object {
        private const val TOTAL_COUNT = 3
    }
}