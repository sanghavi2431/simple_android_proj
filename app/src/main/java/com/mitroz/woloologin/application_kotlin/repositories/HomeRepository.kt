package com.mitroz.woloologin.application_kotlin.repositories


import com.mitroz.woloologin.application_kotlin.api_classes.ApiResponseData
import com.mitroz.woloologin.application_kotlin.api_classes.ApiService
import com.mitroz.woloologin.application_kotlin.api_classes.ApiServiceCallback
import com.mitroz.woloologin.application_kotlin.api_classes.ApiServiceClientAdapter
import com.mitroz.woloologin.application_kotlin.api_classes.BaseResponse
import com.mitroz.woloologin.application_kotlin.api_classes.MessageResponse
import com.mitroz.woloologin.application_kotlin.api_classes.WebserviceCallback
import com.mitroz.woloologin.application_kotlin.repositories.BaseRepository
import `in`.woloo.www.application_kotlin.model.lists_models.Voucher

import org.json.JSONObject
import retrofit2.Call

class HomeRepository : BaseRepository() {

    val service: ApiService = ApiServiceClientAdapter.instance.apiService


}