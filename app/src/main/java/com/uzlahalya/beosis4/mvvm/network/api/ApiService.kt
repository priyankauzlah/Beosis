package com.uzlahalya.beosis4.mvvm.network.api

import com.uzlahalya.beosis4.model.ArticleResponse
import com.uzlahalya.beosis4.model.ScholarshipResponse
import retrofit2.http.GET

interface ApiService {
    @GET("Scholarship")
    suspend fun getDataScholarship(): ScholarshipResponse

    @GET("Article")
    suspend fun getDataArticle(): ArticleResponse
}