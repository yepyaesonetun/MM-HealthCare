package com.yammobots.mm_healthcare.persistence.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.yammobots.mm_healthcare.data.vo.HealthCareInfoVO

/**
 * Created by yepyaesonetun on 7/11/18.
 **/
@Dao
interface HealthCareInfoDao {

    @Query("SELECT * FROM healthCareInfo")
    fun allHealCareInfoList() : List<HealthCareInfoVO>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertHealthCareInfo(healthCareInfoVOs : List<HealthCareInfoVO>) : LongArray

    @Query("SELECT * FROM healthCareInfo WHERE id = :id")
    fun getHealCareInfoById(id: Int): HealthCareInfoVO

    @Query("SELECT * FROM healthCareInfo WHERE id = :id")
    fun getHealCareInfoByIdLD(id : Int) : HealthCareInfoVO

}