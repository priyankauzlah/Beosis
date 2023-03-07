package com.uzlahalya.beosis4.mvvm.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "SCHOLARSHIP_TABLE_NAME")
data class ScholarshipEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val logo: String,
    val name: String,
    val university: String,
    val country: String,
    val majors: String,
    val degree: String,
    val closeregistration: String,
    val link: String,
    val openregistration: String,
    val benefit : String,
    val requirement : String
)
