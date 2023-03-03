package com.uzlahalya.beosis4.mvvm

import com.google.gson.GsonBuilder
import com.uzlahalya.beosis4.mvvm.network.api.ApiArticle
import com.uzlahalya.beosis4.mvvm.network.api.ApiScholarship
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiService {

    val BASE_URL: String = "https://beosisapp.smkidnakhwat.com/api/"

    private fun getRetrofit() : Retrofit{
        return Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()
    }

     fun getService(): ApiScholarship {
            return getRetrofit().create(ApiScholarship::class.java)
        }

    fun getArticleService(): ApiArticle {
        return getRetrofit().create(ApiArticle::class.java)
    }
}