package com.uzlahalya.beosis4.mvvm

import android.util.Log
import com.uzlahalya.beosis4.model.ScholarshipResponse
import com.uzlahalya.beosis4.mvvm.network.api.ApiScholarship
import com.uzlahalya.beosis4.mvvm.network.handler.NetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val scholarshipApi : ApiScholarship) {
    suspend fun getScholarship():Flow<NetworkResult<ScholarshipResponse>> = flow {
        try {
            emit(NetworkResult.Loading(true))
            val scholarship = scholarshipApi.getDataScholarship()

            if (scholarship.isSuccessful){
                val responseBody = scholarship.body()

                if (responseBody?.scholarship?.isEmpty() == true){
                    emit(NetworkResult.Error("Scholarship List not found"))
                }else{
                    emit(NetworkResult.Success(responseBody!!))
                }
            }else{
                Log.d("apiServiceError", "statusCode:${scholarship.code()}, message:${scholarship.message()}")
                emit(NetworkResult.Error("Failed to fetch data from server"))
            }
        } catch (err: Exception){
            err.printStackTrace()
            Log.d("remoteError", "${err.message}")
            emit(NetworkResult.Error("Something went wrong"))
        }
    }.flowOn(Dispatchers.IO)
}