package com.yammobots.mm_healthcare.data.vo

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.arch.persistence.room.TypeConverters
import com.google.gson.annotations.SerializedName
import com.yammobots.mm_healthcare.persistence.typeconverters.AuthorTypeConverter

/**
 * Created by yepyaesonetun on 7/10/18.
 **/

@Entity(tableName = "healthCareInfo")
@TypeConverters(AuthorTypeConverter::class)
class HealthCareInfoVO(@PrimaryKey var id: Int = 0,
                       var image: String? = null,
                       @SerializedName("complete-url")
                       var completeUrl: String? = null,
                       var title: String? = null,
                       @SerializedName("published-date")
                       var publishedDate: String? = null,
                       @SerializedName("short-description")
                       var shortDescription: String? = null,
                       var author: AuthorVO? = null) {

}