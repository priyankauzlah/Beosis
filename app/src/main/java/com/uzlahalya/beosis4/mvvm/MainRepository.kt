package com.uzlahalya.beosis4.mvvm

import com.uzlahalya.beosis4.data.Article
import com.uzlahalya.beosis4.data.Scholarship
import com.uzlahalya.beosis4.mvvm.database.ScholarshipDao
import com.uzlahalya.beosis4.mvvm.database.ScholarshipDatabase
import com.uzlahalya.beosis4.mvvm.network.api.ApiService
import com.uzlahalya.beosis4.util.ContextProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class MainRepository {

    private val scholarshipDao: ScholarshipDao by lazy {
        val database = ScholarshipDatabase.getDatabase(ContextProvider.get())
        database.scholarshipDao()
    }

    private val apiService: ApiService by lazy {
        return@lazy ApiSource.getApiService()
    }

    fun getScholarships(): Flow<List<Scholarship>> {
        return flow {
            try {
                emit(apiService.getDataScholarship().map())
            } catch (e: Throwable) {
                throw e
            }
        }
    }

    fun getArticles(): Flow<List<Article>> {
        return flow {
            try {
                emit(apiService.getDataArticle().map())
            } catch (e: Throwable) {
                throw e
            }
        }
    }

    fun getSavedScholarships(): Flow<List<Scholarship>> = scholarshipDao.listScholarship().map { it.map() }
    fun insertScholarship(scholarship: Scholarship) = scholarshipDao.insertScholarship(scholarship.toEntity())
    fun deleteScholarship(scholarship: Scholarship) = scholarshipDao.deleteScholarship(scholarship.toEntity())
}