package com.mitroz.woloologin.application_kotlin.view_models

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import `in`.woloo.www.application_kotlin.model.server_response.AuthConfigResponse
import com.mitroz.woloologin.application_kotlin.repositories.BaseRepository
import com.mitroz.woloologin.application_kotlin.api_classes.ApiResponseData
import com.mitroz.woloologin.application_kotlin.api_classes.BaseResponse
import com.mitroz.woloologin.application_kotlin.api_classes.EventLiveData
import com.mitroz.woloologin.application_kotlin.api_classes.WebserviceCallback
import `in`.woloo.www.application_kotlin.utilities.ProgressBarAttr
import `in`.woloo.www.application_kotlin.model.lists_models.ReverseGeocodeItem
import `in`.woloo.www.application_kotlin.model.lists_models.LocaleRequest

open class BaseViewModel: ViewModel() {

    private val baseAPIRepository: BaseRepository = BaseRepository()
    private val mProgressDialogLd = MutableLiveData<ProgressBarAttr>()
    private val mNetworkErrorLd = MutableLiveData<ApiResponseData<*>>()
    private val mAppConfig = MutableLiveData<AuthConfigResponse.Data?>()
    private val reverseGeocodeResponse: EventLiveData<BaseResponse<ArrayList<ReverseGeocodeItem>>> = EventLiveData()


    fun observeProgressDialogLiveData(): MutableLiveData<ProgressBarAttr> {
        return mProgressDialogLd
    }

    fun observeNetworkDetectorLiveData(): MutableLiveData<ApiResponseData<*>> {
        return mNetworkErrorLd
    }

    fun observeAppConfig(): MutableLiveData<AuthConfigResponse.Data?>{
        return mAppConfig
    }

    fun getAppConfig(request: LocaleRequest){
        baseAPIRepository.appConfig(request, object :
            WebserviceCallback<ApiResponseData<BaseResponse<AuthConfigResponse.Data>>> {
            override fun onWebResponse(data: ApiResponseData<BaseResponse<AuthConfigResponse.Data>>) {
                if(data.status == ApiResponseData.API_SUCCESS && data.data != null) {
                    mAppConfig.value = data.data!!.data!!
                } else {
                    mAppConfig.value = null
                }
            }
        })
    }


    fun observeReverseGeocoding(): EventLiveData<BaseResponse<ArrayList<ReverseGeocodeItem>>> {
        return reverseGeocodeResponse
    }

    fun updateProgress(show: Boolean) {
        val progress = ProgressBarAttr()
        progress.isShow = show
        mProgressDialogLd.value = progress
    }

    protected fun notifyNetworkError(error: ApiResponseData<*>) {
        mNetworkErrorLd.value = error
    }
}