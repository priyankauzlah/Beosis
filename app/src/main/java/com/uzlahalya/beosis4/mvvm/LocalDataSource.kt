package com.uzlahalya.beosis4.mvvm

import com.uzlahalya.beosis4.mvvm.database.ScholarshipDao
import com.uzlahalya.beosis4.mvvm.database.ScholarshipEntity
import kotlinx.coroutines.flow.Flow

class LocalDataSource (private val dao: ScholarshipDao) {
    suspend fun insertGame(gameEntity: ScholarshipEntity) = dao.insertScholarship(gameEntity)
    fun listGame(): Flow<List<ScholarshipEntity>> = dao.listScholarship()
    suspend fun deleteGame(gameEntity: ScholarshipEntity) = dao.deleteScholarship(gameEntity)
}