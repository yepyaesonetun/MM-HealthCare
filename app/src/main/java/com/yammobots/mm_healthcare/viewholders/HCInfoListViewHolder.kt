package com.yammobots.mm_healthcare.viewholders

import android.view.View
import com.bumptech.glide.request.RequestOptions
import com.yammobots.mm_healthcare.R
import com.yammobots.mm_healthcare.data.vo.AuthorVO
import com.yammobots.mm_healthcare.data.vo.HealthCareInfoVO
import com.yammobots.mm_healthcare.delegates.HealthCareInfoItemDelegate
import com.yammobots.mm_healthcare.utils.GlideApp
import kotlinx.android.synthetic.main.item_hc_info_view.view.*

/**
 * Created by yepyaesonetun on 7/11/18.
 **/
class HCInfoListViewHolder(itemView: View, private val delegate: HealthCareInfoItemDelegate)
    : BaseViewHolder<HealthCareInfoVO> (itemView){

    private var healthCareInfoVO : HealthCareInfoVO? = null
    private var authorVO : AuthorVO? = null

    override fun setData(data: HealthCareInfoVO) {
        healthCareInfoVO = data
        authorVO = data.author

        itemView.tvTitle.text = data.title
        itemView.tvBrief.text = data.shortDescription
        itemView.tvPublishedDate.text = data.publishedDate
        itemView.tvAuthorName.text = authorVO!!.authorName

        // load news image
        GlideApp.with(itemView)
                .load(data.image)
                .placeholder(R.drawable.sample)
                .into(itemView.ivInfoImage)

        // load author profile image
        GlideApp.with(itemView.ivAuthorProfileImage.context)
                .load(authorVO!!.authorPicture)
                .apply(RequestOptions.circleCropTransform())
                .error(R.drawable.user)
                .into(itemView.ivAuthorProfileImage)

    }

    override fun onClick(v: View?) {
       delegate.onTapHealthCareInfoItem(healthCareInfoVO!!.completeUrl!!)
    }
}