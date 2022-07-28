package com.example.spacex.utils

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.spacex.R
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

fun buildRetrofit(baseUrl: String, timeOut: Long): Retrofit {
    return Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(okHttpSpace(timeOut))
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

private fun okHttpSpace(timeOut: Long): OkHttpClient {
    val loggingInterceptor = HttpLoggingInterceptor()
    loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
    return OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .readTimeout(timeOut, TimeUnit.MILLISECONDS)
        .connectTimeout(timeOut, TimeUnit.MILLISECONDS)
        .build()
}


fun alertDialogLoading(
    context: Context?,
    inflater: LayoutInflater,
    text: String?
): AlertDialog? {
    val dialogBuilder =
        AlertDialog.Builder(context, android.R.style.Theme_DeviceDefault_Dialog_NoActionBar)
    val dialogView: View = inflater.inflate(R.layout.layout_up, null)
    dialogBuilder.setView(dialogView)
    val title = dialogView.findViewById<TextView>(R.id.title)
    if (text == null) title.visibility = View.GONE else {
        title.visibility = View.VISIBLE
        title.text = text
    }
    val b = dialogBuilder.create()
    b.setCanceledOnTouchOutside(false)
    b.setCancelable(false)
    return b
}

fun ImageView.loadGlide(context: Context?, path: String) {

    if (context != null) {
        Glide.with(context)
            .load(path)
            .override(100,100)
            .into(this)
    }
}


fun loadUrl(url:String,context: Context){
    val intent = Intent(Intent.ACTION_VIEW)
    intent.data = Uri.parse(url)
    context.startActivity(intent)
}
