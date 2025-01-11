package com.mitroz.woloologin.application_kotlin.api_classes

import android.os.Build

import com.google.gson.GsonBuilder

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.*
import java.util.concurrent.TimeUnit



class ApiServiceClientAdapter {

    val apiService: ApiService
        get() = apiServices

    init {
        createAdapter()
    }

    private fun createAdapter() {
        mOkHttpClient = OkHttpClient.Builder()
        mOkHttpClient.connectTimeout(2, TimeUnit.MINUTES)
        mOkHttpClient.readTimeout(2, TimeUnit.MINUTES)
        mOkHttpClient.writeTimeout(2, TimeUnit.MINUTES)
        mOkHttpClient.addInterceptor(RequestInterceptor())
        mOkHttpClient.followRedirects(true)
        mOkHttpClient.followSslRedirects(true)
        mOkHttpClient.retryOnConnectionFailure(true)
        mOkHttpClient.cache(null).build()


        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        mOkHttpClient.addInterceptor(interceptor)

        val gson = GsonBuilder()
            .setLenient()
            .create()

        val retrofit = Retrofit.Builder()
            .client(mOkHttpClient.build())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl("https://api.woloo.in/")
            .build()
        apiServices = retrofit.create(ApiService::class.java)
    }

    class RequestInterceptor : Interceptor {
        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain): Response {
            var request = chain.request().newBuilder()
//            request.addHeader("content-type", "application/json")
            val slash = "/"
            val android = "Android"
            val userAgent = android + slash + "22110" + "/" + Build.VERSION.RELEASE
            request.addHeader("user-agent", userAgent)

            return chain.proceed(request.build())
        }
    }

    companion object {
        private lateinit var apiServices: ApiService
        private var mApiServiceClientAdapter: ApiServiceClientAdapter? = null
        private lateinit var mOkHttpClient: OkHttpClient.Builder
        val instance: ApiServiceClientAdapter
            get() {
                if (mApiServiceClientAdapter == null) {
                    mApiServiceClientAdapter = ApiServiceClientAdapter()
                }
                return mApiServiceClientAdapter!!
            }
    }
}