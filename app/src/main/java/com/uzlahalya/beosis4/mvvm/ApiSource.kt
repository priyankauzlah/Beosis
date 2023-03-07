package com.uzlahalya.beosis4.mvvm

import com.uzlahalya.beosis4.mvvm.network.api.ApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiSource {

    companion object {
        const val BASE_URL: String = "https://beosisapp.smkidnakhwat.com/api/"

        private fun getRetrofit() : Retrofit{
            return Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()
        }

        fun getApiService(): ApiService {
            return getRetrofit().create(ApiService::class.java)
        }
    }
}