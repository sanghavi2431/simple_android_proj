package com.mitroz.woloologin.application_kotlin.view_models

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import `in`.woloo.www.application_kotlin.model.server_request.SendOtpRequest
import `in`.woloo.www.application_kotlin.model.server_response.SendOtpResponse
import `in`.woloo.www.application_kotlin.model.server_request.VerifyOtpRequest
import `in`.woloo.www.application_kotlin.model.server_response.VerifyOtpResponse
import com.mitroz.woloologin.application_kotlin.repositories.LoginRepository
import com.mitroz.woloologin.application_kotlin.api_classes.ApiResponseData
import com.mitroz.woloologin.application_kotlin.api_classes.BaseResponse
import com.mitroz.woloologin.application_kotlin.api_classes.EventLiveData
import com.mitroz.woloologin.application_kotlin.api_classes.WebserviceCallback

class WolooViewModel: BaseViewModel() {

    private var sendOTPServicesLiveData: MutableLiveData<SendOtpResponse>? = null
    private var verifyOTPServicesLiveData: MutableLiveData<VerifyOtpResponse>? = null
    private val mLoginRepository: LoginRepository = LoginRepository()
    private val mSendOtp: EventLiveData<BaseResponse<SendOtpResponse>> = EventLiveData()
    private var mSendOtpStatus: String = ""
    private val mVerifyOtp: EventLiveData<BaseResponse<VerifyOtpResponse>> = EventLiveData()

    fun sendOtp(
        request: SendOtpRequest
    ) {
        updateProgress(true)
        mLoginRepository.sendOtp(request, object :
            WebserviceCallback<ApiResponseData<BaseResponse<SendOtpResponse>>> {
            override fun onWebResponse(data: ApiResponseData<BaseResponse<SendOtpResponse>>) {
                updateProgress(false)
                mSendOtpStatus = data.message
                Log.d("Response is :- " , mSendOtpStatus)
                if (data.status == ApiResponseData.API_SUCCESS) {
                    mSendOtp.value = data.data
                } else {
                    mSendOtp.value = data.data
                    notifyNetworkError(data)
                }
            }
        })
    }

    fun observeSendOtp(): EventLiveData<BaseResponse<SendOtpResponse>> {
        return mSendOtp
    }

}
