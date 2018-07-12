package com.yammobots.mm_healthcare.mvp.presenters

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.yammobots.mm_healthcare.mvp.views.BaseView

/**
 * Created by yepyaesonetun on 7/11/18.
 **/


abstract class BasePresenter<T : BaseView> : ViewModel() {

    protected lateinit var mView: T
    protected lateinit var mErrorLD: MutableLiveData<String>

    val errorLD: LiveData<String>
        get() = mErrorLD

    open fun initPresenter(mView: T) {
        this.mView = mView
        mErrorLD = MutableLiveData()
    }
}