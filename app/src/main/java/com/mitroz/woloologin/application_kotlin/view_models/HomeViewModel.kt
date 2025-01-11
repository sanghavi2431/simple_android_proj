package com.mitroz.woloologin.application_kotlin.view_models


import com.mitroz.woloologin.application_kotlin.api_classes.BaseResponse
import com.mitroz.woloologin.application_kotlin.api_classes.EventLiveData
import com.mitroz.woloologin.application_kotlin.api_classes.MessageResponse
import `in`.woloo.www.application_kotlin.model.lists_models.Voucher
import com.mitroz.woloologin.application_kotlin.repositories.HomeRepository

import org.json.JSONObject

class HomeViewModel : BaseViewModel() {
    val TAG =javaClass.name

    private val mHomeRepository: HomeRepository = HomeRepository()
    private val mVoucher: EventLiveData<BaseResponse<Voucher>> = EventLiveData()
    private val mWolooEngagement: EventLiveData<BaseResponse<JSONObject>> = EventLiveData()
    private val redeemOfferResponse: EventLiveData<BaseResponse<MessageResponse>> = EventLiveData()



}