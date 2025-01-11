package com.mitroz.woloologin.application_kotlin.api_classes

import android.app.Activity

import org.json.JSONObject
import java.lang.reflect.Type

class NetworkAPICallModel(
    var apiURL: String,
    var request_type: Int,
    var app_type: String,
    var jsonObjectRequest: JSONObject,

) {
    var timeOut = 30 * 1000
    var parserType: Type? = null
    var responseObject: Any? = null
    var customObject: Any? = null
    var onResponseBindCallBack: IOnResponseBindCallBack? = null
    var isShowProgress = true
    var activity: Activity? = null
    var max_try = 0
    var max_try_error = 0
    var encryptedRequest: JSONObject? = null
    var isProgressVisible = false


}
