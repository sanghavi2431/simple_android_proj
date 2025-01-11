package com.mitroz.woloologin.application_kotlin.repositories

import com.mitroz.woloologin.application_kotlin.api_classes.ApiResponseData
import com.mitroz.woloologin.application_kotlin.api_classes.ApiService
import com.mitroz.woloologin.application_kotlin.api_classes.ApiServiceCallback
import com.mitroz.woloologin.application_kotlin.api_classes.ApiServiceClientAdapter
import com.mitroz.woloologin.application_kotlin.api_classes.BaseResponse
import com.mitroz.woloologin.application_kotlin.api_classes.WebserviceCallback

import `in`.woloo.www.application_kotlin.model.server_response.AuthConfigResponse
import `in`.woloo.www.application_kotlin.model.lists_models.ReverseGeocodeItem
import `in`.woloo.www.application_kotlin.model.lists_models.LocaleRequest
import `in`.woloo.www.application_kotlin.utilities.NetworkUtils
import retrofit2.Call

open class BaseRepository {

    val apiService: ApiService = ApiServiceClientAdapter.instance.apiService

    fun appConfig(
        request: LocaleRequest,
        webserviceCallback: WebserviceCallback<ApiResponseData<BaseResponse<AuthConfigResponse.Data>>>
    ) {

            try {
                val call: Call<BaseResponse<AuthConfigResponse.Data>> =
                    apiService.appConfig(request)
                val callback: ApiServiceCallback<BaseResponse<AuthConfigResponse.Data>> =
                    ApiServiceCallback(webserviceCallback)
                call.enqueue(callback)
            } catch (e: Exception){

            }


    }


}