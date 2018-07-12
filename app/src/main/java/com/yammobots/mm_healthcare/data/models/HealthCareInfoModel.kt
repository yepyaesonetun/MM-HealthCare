package com.yammobots.mm_healthcare.data.models

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.content.Context
import com.yammobots.mm_healthcare.data.vo.HealthCareInfoVO
import com.yammobots.mm_healthcare.network.responses.HealthCareInfoResponse
import com.yammobots.mm_healthcare.utils.AppConstants
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable

import io.reactivex.schedulers.Schedulers
import org.reactivestreams.Subscriber
import org.reactivestreams.Subscription

/**
 * Created by yepyaesonetun on 7/10/18.
 **/
class HealthCareInfoModel private constructor(context: Context) : BaseModel(context) {

    private var pageIndex: Int

    companion object {
        private var INSTANCE: HealthCareInfoModel? = null
        fun getInstance(): HealthCareInfoModel {
            if (INSTANCE == null) {
                throw RuntimeException("HealthCareInfoModel is being invoke before initializing")
            }

            val i = INSTANCE
            return i!!
        }

        fun initHealthCareInfoModel(context: Context) {
            INSTANCE = HealthCareInfoModel(context)
        }
    }

    init {
        pageIndex = 1
    }

    /**
     * network call with RxJava + Retrofit and
     * store data to persistence Layer and
     * sent result value to LiveData
     */
    fun startLoadingHealthCareInfo(pageNo: Int, healthCareInfoListLD: MutableLiveData<List<HealthCareInfoVO>>,
                                   errorLD: MutableLiveData<String>) {
        mTheAPI.loadHealCareInfo(AppConstants.ACCESS_TOKEN)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : io.reactivex.Observer<HealthCareInfoResponse> {
                    override fun onNext(healthCareInfoResponse: HealthCareInfoResponse) {
                        if (healthCareInfoResponse!!.getInfoList().isNotEmpty()) {

                            // persist to DB
                            persistHealthCareInfo(healthCareInfoResponse!!.getInfoList())

                            // set value to Live Data
                            healthCareInfoListLD.setValue(healthCareInfoResponse!!.getInfoList())
                        } else {
                            errorLD.setValue("No data could be loaded for now. Pls try again later.")
                        }
                    }

                    override fun onSubscribe(d: Disposable) {

                    }


                    override fun onError(e: Throwable) {
                        errorLD.setValue(e.message)
                    }

                    override fun onComplete() {

                    }
                })
    }

    /**
     * store Data to DB
     */
    private fun persistHealthCareInfo(infoList: List<HealthCareInfoVO>) {
        mTheDB.healthCareInfoDao().insertHealthCareInfo(infoList)
    }

}