package com.uzlahalya.beosis4.mvvm.database

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

interface ScholarshipDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertScholarship(scholarshipEntity: ScholarshipEntity)

    @Query("SELECT * FROM scholarship_table ORDER BY id ASC")
    fun listScholarship(): Flow<List<ScholarshipEntity>>

    @Delete()
    suspend fun deleteScholarship(scholarshipEntity: ScholarshipEntity)
}