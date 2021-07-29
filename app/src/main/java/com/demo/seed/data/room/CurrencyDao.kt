package com.demo.seed.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.demo.seed.data.model.CurrencyInfoEntity

@Dao
interface CurrencyDao {

    @Query("SELECT * FROM currency_info_table")
    fun getCurrencyInfoList(): List<CurrencyInfoEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(task: CurrencyInfoEntity)
}