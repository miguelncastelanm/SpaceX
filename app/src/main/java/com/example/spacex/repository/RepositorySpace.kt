package com.example.spacex.repository

import com.example.spacex.datasource.DatasourceSpace
import com.example.spacex.model.responseData
import com.google.gson.Gson

class RepositorySpace {
    private val dataSource = DatasourceSpace()
    private val gson = Gson()

    suspend fun getLatest(url: String, timeout:Long): responseData {
        return dataSource.getLatest(url,timeout)
    }

}