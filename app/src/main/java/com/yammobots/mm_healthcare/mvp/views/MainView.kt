package com.yammobots.mm_healthcare.mvp.views

import com.yammobots.mm_healthcare.data.vo.HealthCareInfoVO

/**
 * Created by yepyaesonetun on 7/11/18.
 **/
interface MainView : BaseView{

    fun displayHCInfoData(healthCareInfoVOS: List<HealthCareInfoVO>)
    abstract fun launchChromeCustomTabView(webUrl : String)
}