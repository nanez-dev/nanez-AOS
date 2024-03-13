package com.nane.detail.presentation.view.adapter

import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.nane.base.databinding.CommonBindingAdapter.setLoadImage
import com.nane.detail.databinding.PerfumeDetailAccordViewBinding
import com.nane.detail.databinding.PerfumeDetailBasicViewBinding
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
                imgAccord.setLoadImage(it.imageUrl, 12)
                txtAccord.text = it.engName
            }
            data.getOrNull(1)?.let {
                vgAccordSecond.visibility = View.VISIBLE
                imgAccordSecond.setLoadImage(it.imageUrl, 12)
                txtAccordSecond.text = it.engName
            }
            data.getOrNull(2)?.let {
                vgAccordThird.visibility = View.VISIBLE
                imgAccordThird.setLoadImage(it.imageUrl, 12)
                txtAccordThird.text = it.engName
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