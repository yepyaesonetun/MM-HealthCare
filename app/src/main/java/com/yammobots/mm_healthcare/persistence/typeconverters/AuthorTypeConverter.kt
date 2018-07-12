package com.yammobots.mm_healthcare.persistence.typeconverters

import android.arch.persistence.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.yammobots.mm_healthcare.data.vo.AuthorVO

/**
 * Created by yepyaesonetun on 7/11/18.
 **/
class AuthorTypeConverter {

    @TypeConverter
    fun toString(authorVO: AuthorVO): String {
        return Gson().toJson(authorVO)
    }

    @TypeConverter
    fun toAuthor(authorJson : String) : AuthorVO {
        val authorType = object : TypeToken<AuthorVO>(){}.type
        return Gson().fromJson(authorJson, authorType)
    }

}