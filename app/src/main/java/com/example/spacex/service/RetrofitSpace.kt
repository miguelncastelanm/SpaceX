package com.example.spacex.service

import com.example.spacex.utils.buildRetrofit

class RetrofitSpace (private val baseUrl: String, private val timeOut: Long) {

    val webService by lazy {
        buildRetrofit(baseUrl, timeOut).create(WebService::class.java)
    }

}