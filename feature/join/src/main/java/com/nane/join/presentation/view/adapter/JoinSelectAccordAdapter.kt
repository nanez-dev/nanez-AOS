package com.nane.join.presentation.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nane.join.databinding.JoinSelectAccordItemViewBinding
import com.nane.join.presentation.data.JoinAccordViewData
import org.techtown.nanez.utils.util.GlideImageLoadData
import org.techtown.nanez.utils.util.GlideUtil

/**
 * Created by haul on 11/20/23
 */
class JoinSelectAccordAdapter : RecyclerView.Adapter<JoinSelectAccordAdapter.JoinSelectAccordViewHolder>() {

    private var dataList: List<JoinAccordViewData>? = null

    fun setDataList(list: List<JoinAccordViewData>?) {
        dataList = list
        notifyDataSetChanged()
    }

    var userActionsListener: UserActionsListener? = null
    interface UserActionsListener {
        fun onSelectAccord(targetId: Int)
    }

    private fun changeSelectTarget(target: JoinAccordViewData) {
        var selectId = -1

        dataList?.forEach {
            if (it.id == target.id) {
                it.isSelected = target.isSelected
                selectId = if (it.isSelected) it.id else -1
            } else {
                it.isSelected = false
            }
        }
        userActionsListener?.onSelectAccord(selectId)
        notifyItemRangeChanged(0, dataList?.size ?: 0, dataList)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JoinSelectAccordViewHolder {
        return JoinSelectAccordViewHolder(JoinSelectAccordItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: JoinSelectAccordViewHolder, position: Int) {
        dataList?.getOrNull(position)?.let {
            holder.onBind(it)
        }
    }

    override fun getItemCount() = dataList?.size ?: 0


    inner class JoinSelectAccordViewHolder(private val binding: JoinSelectAccordItemViewBinding) : RecyclerView.ViewHolder(binding.root) {

        private var data: JoinAccordViewData? = null

        init {
            binding.apply {
                vgParent.setOnClickListener { view ->
                    data?.let {
                        it.isSelected = !it.isSelected
                        changeSelectTarget(it)
                    }
                }
            }
        }

        fun onBind(data: JoinAccordViewData) {
            this.data = data
            binding.txtAccordName.text = data.name
            GlideUtil.instance.displayImage(GlideImageLoadData(imageView = binding.imgIcon, imageUrl = data.imgUrl))

            binding.vgParent.alpha = if (data.isSelected) 1f else 0.3f
        }

    }
}