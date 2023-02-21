package com.uzlahalya.beosis4.utils

import com.uzlahalya.beosis4.utils.network.ApiEndPoint
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiService {

        val BASE_URL: String = "https://beosis.smkidnakhwat.com/"



        private fun getretrofit() : Retrofit {
                return Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

            }

    fun getService(): ApiEndPoint{
        return getretrofit().create(ApiEndPoint::class.java)
    }


}