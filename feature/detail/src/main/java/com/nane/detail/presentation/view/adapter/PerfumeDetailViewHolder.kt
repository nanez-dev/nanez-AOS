package com.nane.detail.presentation.view.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.nane.base.databinding.CommonBindingAdapter.setLoadImage
import com.nane.detail.databinding.PerfumeDetailAccordViewBinding
import com.nane.detail.databinding.PerfumeDetailBasicViewBinding
import com.nane.detail.databinding.PerfumeDetailNoteViewBinding
import com.nane.detail.presentation.data.PerfumeDetailViewData
import com.nane.detail.presentation.viewmodel.PerfumeDetailViewModel
import org.techtown.nanez.utils.util.GlideImageLoadData
import org.techtown.nanez.utils.util.GlideUtil
import org.techtown.nanez.utils.util.ResUtils
import java.text.DecimalFormat

/**
 * Created by haul on 3/10/24
 */
class PerfumeDetailBasicViewHolder(
    binding: PerfumeDetailBasicViewBinding,
    vm: PerfumeDetailViewModel,
) : AbsPerfumeDetailViewHolder<PerfumeDetailViewData.Basic, PerfumeDetailBasicViewBinding>(binding) {

    private var perfumeId = 0

    init {
        itemViewBinding.apply {
            vgWish.setOnClickListener {
                vm.onChangeWish(perfumeId)
            }

            vgHaving.setOnClickListener {
                vm.onChangHaving(perfumeId)
            }
        }
    }

    override fun onBind(data: PerfumeDetailViewData.Basic) {
        perfumeId = data.id

        itemViewBinding.apply {
            GlideUtil.instance.displayImage(GlideImageLoadData(imgPerfume, data.imageUrl))
            txtBrand.text = data.brandName
            txtNameEng.text = data.engName
            txtNameKor.text = ResUtils.instance.getString(com.nane.base.R.string.label_perfume_detail_kor_name, data.korName ?: "", data.capacity ?: "0")

            txtPrice.text = DecimalFormat("#,###").format(data.price)

            setViewData(data.isWish, imgWish, txtWish)
            setViewData(data.isHaving, imgHaving, txtHaving)
        }
    }


    private fun setViewData(isSelected: Boolean, imgView: AppCompatImageView, textView: AppCompatTextView) {
        val colorRes = if (isSelected) com.nane.base.R.color.brand_500 else com.nane.base.R.color.gray_800
        imgView.setColorFilter(ResUtils.instance.getColor(colorRes))
        textView.setTextColor(ResUtils.instance.getColor(colorRes))
    }
}


class PerfumeDetailAccordViewHolder(
    binding: PerfumeDetailAccordViewBinding
) : AbsPerfumeDetailViewHolder<List<PerfumeDetailViewData.Accord>, PerfumeDetailAccordViewBinding>(binding) {

    override fun onBind(data: List<PerfumeDetailViewData.Accord>) {
        itemViewBinding.run {
            setViewData(data.getOrNull(0), vgAccordOne, imgAccord, txtAccord)
            setViewData(data.getOrNull(1), vgAccordSecond, imgAccordSecond, txtAccordSecond)
            setViewData(data.getOrNull(2), vgAccordThird, imgAccordThird, txtAccordThird)
        }
    }

    private fun setViewData(data: PerfumeDetailViewData.Accord?, viewGroup: ViewGroup, imgView: AppCompatImageView, textView: AppCompatTextView) {
        data?.let {
            viewGroup.visibility = View.VISIBLE
            imgView.setLoadImage(it.imageUrl, 12)
            textView.text = it.engName
        } ?: {
            viewGroup.visibility = View.GONE
        }
    }
}


class PerfumeDetailNoteViewHolder(
    binding: PerfumeDetailNoteViewBinding
) : AbsPerfumeDetailViewHolder<PerfumeDetailViewData.Note, PerfumeDetailNoteViewBinding>(binding) {

    override fun onBind(data: PerfumeDetailViewData.Note) {
        itemViewBinding.run {
            txtNoteTitle.text = data.title
            txtNoteContent.text = data.subTitle

            setEtcView(vgTopNoteEtc, txtTopNoteEtc, data.allTopNoteStr)
            setEtcView(vgMiddleNoteEtc, txtMiddleNoteEtc, data.allMiddleNoteStr)
            setEtcView(vgBaseNoteEtc, txtBaseNoteEtc, data.allBaseNoteStr)

            setNoteMainView(data.topNote, vgTopNote, imgTopNote, txtTopNoteName)
            setNoteMainView(data.middleNote, vgMiddleNote, imgMiddleNote, txtMiddleNoteName)
            setNoteMainView(data.baseNote, vgBaseNote, imgBaseNote, txtBaseNoteName)
        }
    }

    private fun setEtcView(viewGroup: ViewGroup, textView: AppCompatTextView, content: String?) {
        if (content.isNullOrEmpty()) {
            viewGroup.visibility = View.GONE
        } else {
            viewGroup.visibility = View.VISIBLE
            textView.text = content
        }
    }

    private fun setNoteMainView(data: PerfumeDetailViewData.Note.Detail?, viewGroup: ViewGroup, imageView: AppCompatImageView, textView: AppCompatTextView) {
        data?.let {
            viewGroup.visibility = View.VISIBLE
            imageView.setLoadImage(it.imageUrl, 12)
            textView.text = it.name
        } ?: run {
            viewGroup.visibility = View.GONE
        }
    }
}


class PerfumeEmptyViewHolder(view: View) : RecyclerView.ViewHolder(view)



abstract class AbsPerfumeDetailViewHolder<T, V: ViewDataBinding>(
    protected val itemViewBinding: V
) : RecyclerView.ViewHolder(itemViewBinding.root) {
    abstract fun onBind(data: T)
}