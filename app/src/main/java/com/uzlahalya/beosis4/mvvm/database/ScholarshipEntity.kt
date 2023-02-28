package com.uzlahalya.beosis4.mvvm.database

import ScholarshipItem
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "SCHOLARSHIP_TABLE_NAME")
@Parcelize
data class ScholarshipEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val scholarship: ScholarshipItem
): Parcelable
