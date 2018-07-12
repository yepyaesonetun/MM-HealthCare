package com.yammobots.mm_healthcare.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.yammobots.mm_healthcare.R
import com.yammobots.mm_healthcare.data.vo.HealthCareInfoVO
import com.yammobots.mm_healthcare.viewholders.BaseViewHolder
import com.yammobots.mm_healthcare.viewholders.PagerViewHolder
import com.yammobots.mm_healthcare.viewholders.*

/**
 * Created by yepyaesonetun on 7/10/18.
 **/
class HomeRVAdapter(context: Context) : BaseRecyclerAdapter<BaseViewHolder<HealthCareInfoVO>, HealthCareInfoVO>(context) {

    companion object {
        val VT_PAGER : Int = 1
        val VT_CATEGORY : Int = 2
        val VT_LIST : Int = 3
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<HealthCareInfoVO> {
//        return when(viewType){
//            VT_PAGER -> PagerViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.layout_item_pager, parent, false))
//            VT_CATEGORY -> CategoriesViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.layout_item_category, parent, false))
//            VT_LIST -> HCInfoListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.layout_item_pager, parent, false))
//            else -> throw IllegalArgumentException("Invalid View Type!")
//        }
        return null!!
    }
}