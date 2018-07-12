package com.yammobots.mm_healthcare.network

import com.yammobots.mm_healthcare.network.responses.HealthCareInfoResponse
import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 * Created by yepyaesonetun on 7/10/18.
 **/
interface MMHealthCareAPI {

    @FormUrlEncoded
    @POST("mm-healthcare/GetHealthcareInfo.php")
    fun loadHealCareInfo(
            @Field("access_token") accessToken: String
    ): Observable<HealthCareInfoResponse>
}