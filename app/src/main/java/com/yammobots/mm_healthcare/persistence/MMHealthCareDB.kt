package com.yammobots.mm_healthcare.persistence

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.yammobots.mm_healthcare.data.vo.HealthCareInfoVO
import com.yammobots.mm_healthcare.persistence.dao.HealthCareInfoDao

/**
 * Created by yepyaesonetun on 7/11/18.
 **/

@Database(entities = [(HealthCareInfoVO::class)], version = 2, exportSchema = false)
abstract class MMHealthCareDB : RoomDatabase() {

    companion object {
        val DB_NAME: String = "MM_HEALTH_CARE_DB.DB"
        var DB_INSTANCE: MMHealthCareDB? = null
        fun getDatabase(context: Context): MMHealthCareDB {
            if (DB_INSTANCE == null) {
                DB_INSTANCE = Room.databaseBuilder(context, MMHealthCareDB::class.java, DB_NAME)
                        .allowMainThreadQueries()
                        .build()
            }
            val i = DB_INSTANCE
            return i!!
        }
    }

    // define DAOs
    abstract fun  healthCareInfoDao() : HealthCareInfoDao
}