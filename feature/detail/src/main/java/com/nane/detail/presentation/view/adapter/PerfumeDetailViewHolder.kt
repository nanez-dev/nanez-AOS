package com.nane.detail.presentation.view.adapter

import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.nane.base.databinding.CommonBindingAdapter.setLoadImage
import com.nane.detail.databinding.PerfumeDetailAccordViewBinding
import com.nane.detail.databinding.PerfumeDetailBasicViewBinding
import com.nane.detail.databinding.PerfumeDetailNoteViewBinding
import com.nane.detail.presentation.data.PerfumeDetailViewData
import org.techtown.nanez.utils.util.GlideImageLoadData
import org.techtown.nanez.utils.util.GlideUtil
import org.techtown.nanez.utils.util.ResUtils
import java.text.DecimalFormat

/**
 * Created by haul on 3/10/24
 */
class PerfumeDetailBasicViewHolder(
    binding: PerfumeDetailBasicViewBinding
) : AbsPerfumeDetailViewHolder<PerfumeDetailViewData.Basic, PerfumeDetailBasicViewBinding>(binding) {

    override fun onBind(data: PerfumeDetailViewData.Basic) {
        itemViewBinding.apply {
            GlideUtil.instance.displayImage(GlideImageLoadData(imgPerfume, data.imageUrl))
            txtBrand.text = data.brandName
            txtNameEng.text = data.engName
            txtNameKor.text = ResUtils.instance.getString(com.nane.base.R.string.label_perfume_detail_kor_name, data.korName ?: "", data.capacity ?: "0")

            txtPrice.text = DecimalFormat("#,###").format(data.price)

            if (data.isWish) {
                imgWish.setColorFilter(ResUtils.instance.getColor(com.nane.base.R.color.brand_500))
                txtWish.setTextColor(ResUtils.instance.getColor(com.nane.base.R.color.brand_500))
            } else {
                imgWish.setColorFilter(ResUtils.instance.getColor(com.nane.base.R.color.gray_800))
                txtWish.setTextColor(ResUtils.instance.getColor(com.nane.base.R.color.gray_800))
            }

            if (data.isHaving) {
                imgHaving.setColorFilter(ResUtils.instance.getColor(com.nane.base.R.color.brand_500))
                txtHaving.setTextColor(ResUtils.instance.getColor(com.nane.base.R.color.brand_500))
            } else {
                imgHaving.setColorFilter(ResUtils.instance.getColor(com.nane.base.R.color.gray_800))
                txtHaving.setTextColor(ResUtils.instance.getColor(com.nane.base.R.color.gray_800))
            }
        }
    }
}


class PerfumeDetailAccordViewHolder(
    binding: PerfumeDetailAccordViewBinding
) : AbsPerfumeDetailViewHolder<List<PerfumeDetailViewData.Accord>, PerfumeDetailAccordViewBinding>(binding) {

    override fun onBind(data: List<PerfumeDetailViewData.Accord>) {
        itemViewBinding.run {
            data.getOrNull(0)?.let {
                vgAccordOne.visibility = View.VISIBLE
                imgAccord.setLoadImage(it.imageUrl, 12)
                txtAccord.text = it.engName
            } ?: run {
                vgAccordOne.visibility = View.GONE
            }
            data.getOrNull(1)?.let {
                vgAccordSecond.visibility = View.VISIBLE
                imgAccordSecond.setLoadImage(it.imageUrl, 12)
                txtAccordSecond.text = it.engName
            } ?: run {
                vgAccordSecond.visibility = View.GONE
            }
            data.getOrNull(2)?.let {
                vgAccordThird.visibility = View.VISIBLE
                imgAccordThird.setLoadImage(it.imageUrl, 12)
                txtAccordThird.text = it.engName
            } ?: run {
                vgAccordThird.visibility = View.GONE
            }
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

            if (data.allTopNoteStr?.isEmpty() == true) {
                vgTopNoteEtc.visibility = View.GONE
            } else {
                vgTopNoteEtc.visibility = View.VISIBLE
                txtTopNoteEtc.text = data.allTopNoteStr
            }

            if (data.allMiddleNoteStr?.isEmpty() == true) {
                vgMiddleNoteEtc.visibility = View.GONE
            } else {
                vgMiddleNoteEtc.visibility = View.VISIBLE
                txtMiddleNoteEtc.text = data.allMiddleNoteStr
            }

            if (data.allBaseNoteStr?.isEmpty() == true) {
                vgBaseNoteEtc.visibility = View.GONE
            } else {
                vgBaseNoteEtc.visibility = View.VISIBLE
                txtBaseNoteEtc.text = data.allBaseNoteStr
            }

            data.topNote?.let {
                vgTopNote.visibility = View.VISIBLE
                imgTopNote.setLoadImage(it.imageUrl, 12)
                txtTopNoteName.text = it.name
            } ?: run {
                vgTopNote.visibility = View.GONE
            }

            data.middleNote?.let {
                vgMiddleNote.visibility = View.VISIBLE
                imgMiddleNote.setLoadImage(it.imageUrl, 12)
                txtMiddleNoteName.text = it.name
            } ?: run {
                vgMiddleNote.visibility = View.GONE
            }

            data.baseNote?.let {
                vgBaseNote.visibility = View.VISIBLE
                imgBaseNote.setLoadImage(it.imageUrl, 12)
                txtBaseNoteName.text = it.name
            } ?: run {
                vgBaseNote.visibility = View.GONE
            }
        }
    }
}


class PerfumeEmptyViewHolder(view: View) : RecyclerView.ViewHolder(view)



abstract class AbsPerfumeDetailViewHolder<T, V: ViewDataBinding>(
    protected val itemViewBinding: V
) : RecyclerView.ViewHolder(itemViewBinding.root) {
    abstract fun onBind(data: T)
}