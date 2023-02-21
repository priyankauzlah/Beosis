package com.uzlahalya.beosis4.util.network

import com.uzlahalya.beosis4.model.ScholarshipResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiEndPoint {
    @GET("lecture/get")
    fun getData(): Call<ScholarshipResponse>

//    @GET("lecture/search/name/{search}")
//    fun searchItem(@Query("search") data: String?): Call<ScholarshipResponse?>?
}