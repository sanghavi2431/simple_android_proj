package com.mitroz.woloologin.application_kotlin.api_classes

import `in`.woloo.www.application_kotlin.model.lists_models.LocaleRequest
import `in`.woloo.www.application_kotlin.model.server_request.SendOtpRequest
import `in`.woloo.www.application_kotlin.model.server_request.VerifyOtpRequest
import `in`.woloo.www.application_kotlin.model.server_response.AuthConfigResponse
import `in`.woloo.www.application_kotlin.model.server_response.SendOtpResponse
import `in`.woloo.www.application_kotlin.model.server_response.VerifyOtpResponse


import retrofit2.Call
import retrofit2.http.*


interface ApiService {



    @POST("/api/wolooGuest/sendOTP")
    fun sendOtp(@Body request: SendOtpRequest): Call<BaseResponse<SendOtpResponse>>

    @POST("/api/wolooGuest/verifyOTP")
    fun verifyOtp(@Body request: VerifyOtpRequest): Call<BaseResponse<VerifyOtpResponse>>

    @POST("/api/wolooGuest/appConfig")
    fun appConfig(@Body request: LocaleRequest): Call<BaseResponse<AuthConfigResponse.Data>>




}