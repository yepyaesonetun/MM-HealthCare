package com.yammobots.mm_healthcare.data.models

import android.content.Context
import com.google.gson.Gson
import com.yammobots.mm_healthcare.network.MMHealthCareAPI
import com.yammobots.mm_healthcare.persistence.MMHealthCareDB
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by yepyaesonetun on 7/10/18.
 **/
abstract class BaseModel protected constructor(context : Context){
    protected var mTheAPI : MMHealthCareAPI
    protected var mTheDB : MMHealthCareDB

    init {
        val okHttpClient = OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build()

        val retrofit = Retrofit.Builder()
                .baseUrl("http://padcmyanmar.com/padc-5/")
                .addConverterFactory(GsonConverterFactory.create(Gson()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build()
        mTheAPI = retrofit.create<MMHealthCareAPI>(MMHealthCareAPI::class.java)
        mTheDB = MMHealthCareDB.getDatabase(context)
    }
}