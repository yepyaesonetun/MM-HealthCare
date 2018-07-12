package com.yammobots.mm_healthcare.network.responses

import com.google.gson.annotations.SerializedName
import com.yammobots.mm_healthcare.data.vo.HealthCareInfoVO
import java.util.ArrayList

/**
 * Created by yepyaesonetun on 7/10/18.
 **/
class HealthCareInfoResponse {

    @SerializedName("code")
    private val code: Int = 0

    @SerializedName("message")
    private val message: String? = null

    @SerializedName("healthcare-info")
    private var infoList: List<HealthCareInfoVO>? = null

    fun getCode(): Int {
        return code
    }

    fun getInfoList(): List<HealthCareInfoVO> {
        if (infoList == null) {
            infoList = ArrayList()
        }
        val newsListVal = infoList
        return newsListVal!!
    }
}