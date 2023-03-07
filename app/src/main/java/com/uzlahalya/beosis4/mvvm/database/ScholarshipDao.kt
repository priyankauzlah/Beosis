package com.uzlahalya.beosis4.mvvm.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ScholarshipDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertScholarship(scholarshipEntity: ScholarshipEntity)

    @Query("SELECT * FROM SCHOLARSHIP_TABLE_NAME ORDER BY id ASC")
    fun listScholarship(): Flow<List<ScholarshipEntity>>

    @Delete
    fun deleteScholarship(scholarshipEntity: ScholarshipEntity)
}