package com.example.spacex.presenter

import com.example.spacex.model.responseData
import com.example.spacex.repository.RepositorySpace

class PresenterSpace {
    private val repository = RepositorySpace()

    suspend fun getLatest(url: String, timeout:Long ): responseData {
        return repository.getLatest(url,timeout)
    }

}