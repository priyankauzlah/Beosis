package com.uzlahalya.beosis4.mvvm.network.api

import com.uzlahalya.beosis4.model.ArticleResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiArticle {

    @GET("Article")
    fun getDataArticle(
    ): Call<ArticleResponse>
}