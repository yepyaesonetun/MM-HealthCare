package com.yammobots.mm_healthcare.mvp.presenters

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.yammobots.mm_healthcare.data.models.HealthCareInfoModel
import com.yammobots.mm_healthcare.data.vo.HealthCareInfoVO
import com.yammobots.mm_healthcare.delegates.HealthCareInfoItemDelegate
import com.yammobots.mm_healthcare.mvp.views.MainView

/**
 * Created by yepyaesonetun on 7/11/18.
 **/
class MainPresenter : BasePresenter<MainView>(), HealthCareInfoItemDelegate {

    companion object {
        val INITIAL_PAGE_INDEX: Int =1
    }

    private var mHealthCareInfoListLD: MutableLiveData<List<HealthCareInfoVO>>? = null

    val healCareInfoList: LiveData<List<HealthCareInfoVO>>? get() = mHealthCareInfoListLD

    override fun initPresenter(view: MainView) {
        super.initPresenter(view)
        mHealthCareInfoListLD = MutableLiveData()
        HealthCareInfoModel.getInstance().startLoadingHealthCareInfo(INITIAL_PAGE_INDEX,mHealthCareInfoListLD!!, mErrorLD)
    }

    fun forceReLoad(){
        HealthCareInfoModel.getInstance().startLoadingHealthCareInfo(INITIAL_PAGE_INDEX,mHealthCareInfoListLD!!, mErrorLD)
    }

    val healthCareInfoListLD : MutableLiveData<List<HealthCareInfoVO>>
        get() = mHealthCareInfoListLD!!

    override fun onTapHealthCareInfoItem(webUrl: String) {
       mView.launchChromeCustomTabView(webUrl)
    }
}
