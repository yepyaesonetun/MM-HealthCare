package com.yammobots.mm_healthcare.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.yammobots.mm_healthcare.R
import com.yammobots.mm_healthcare.data.vo.HealthCareInfoVO
import com.yammobots.mm_healthcare.delegates.HealthCareInfoItemDelegate
import com.yammobots.mm_healthcare.viewholders.BaseViewHolder
import com.yammobots.mm_healthcare.viewholders.HCInfoListViewHolder

/**
 * Created by yepyaesonetun on 7/11/18.
 **/
class HCInfoRVAdapter(context: Context, private val delegate: HealthCareInfoItemDelegate)
    : BaseRecyclerAdapter<HCInfoListViewHolder, HealthCareInfoVO>(context) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<HealthCareInfoVO> {
        return HCInfoListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_hc_info_view, parent, false), delegate)
    }

}