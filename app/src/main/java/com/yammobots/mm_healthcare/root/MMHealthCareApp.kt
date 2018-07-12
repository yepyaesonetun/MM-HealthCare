package com.yammobots.mm_healthcare.root

import android.app.Application
import com.yammobots.mm_healthcare.data.models.HealthCareInfoModel

/**
 * Created by yepyaesonetun on 7/10/18.
 **/
class MMHealthCareApp : Application() {

    override fun onCreate() {
        super.onCreate()

        HealthCareInfoModel.initHealthCareInfoModel(this)
    }
}