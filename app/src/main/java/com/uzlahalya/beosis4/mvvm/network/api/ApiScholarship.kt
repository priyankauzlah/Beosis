package com.uzlahalya.beosis4.mvvm.network.api

import com.uzlahalya.beosis4.model.ArticleResponse
import com.uzlahalya.beosis4.model.ScholarshipResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface ApiScholarship {
    @GET("Scholarship")
    fun getDataScholarship(

    ): Response<ScholarshipResponse>

//    @GET("lecture/search/name/{search}")
//    fun searchItem(@Query("search") data: String?): Call<ScholarshipResponse?>?
}