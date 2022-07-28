package com.example.spacex.datasource

import android.util.Log
import com.example.spacex.model.responseData
import com.example.spacex.service.RetrofitSpace
import com.example.spacex.service.WebService
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers

import kotlinx.coroutines.withContext



class DatasourceSpace {
    val TAG = "Response:"
    private val gson = Gson()

    suspend fun getLatest(url: String, timeout:Long): responseData {
        val client: WebService = RetrofitSpace(url, timeout).webService

        var resp = responseData()

        withContext(Dispatchers.IO){
            try {

                val response = client.getLatest().execute()
                if( response.isSuccessful && response.body() != null ) {
                    //Code 200
                    resp = gson.fromJson(response.body()?.string(), responseData::class.java)
                    resp.success = true

                }
                else if(response.errorBody() != null){
                    resp = gson.fromJson(response.errorBody()?.string(), responseData::class.java)
                    resp.success = false
                }
                else
                    Log.d(TAG, "Servicio no exitoso" )


            }catch (e: Exception){

            }

        }
        return  resp


    }
}