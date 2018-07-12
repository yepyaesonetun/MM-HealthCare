package com.yammobots.mm_healthcare.data.vo

import com.google.gson.annotations.SerializedName

/**
 * Created by yepyaesonetun on 7/10/18.
 **/
class AuthorVO {

    @SerializedName("author-id")
    val authorId: Long = 0

    @SerializedName("author-name")
    var authorName: String? = null

    @SerializedName("author-picture")
    var authorPicture: String? = null
}