package com.demo.seed.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "currency_info_table")
@Parcelize
data class CurrencyInfoEntity(
    @PrimaryKey val id: String,
    val name: String,
    val symbol: String
): Parcelable
