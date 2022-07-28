package com.example.spacex.service

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WebService {


    @GET("launches/latest")
    fun getLatest(
    ): Call<ResponseBody>
}