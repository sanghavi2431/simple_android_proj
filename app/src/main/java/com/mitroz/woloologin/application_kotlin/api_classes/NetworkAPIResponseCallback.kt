package com.mitroz.woloologin.application_kotlin.api_classes

import com.android.volley.VolleyError
import org.json.JSONObject

interface NetworkAPIResponseCallback : NetworkTimeOut, InternetConnection {
    fun onSuccessResponse(response: JSONObject?, networkAPICallModel: NetworkAPICallModel?)
    fun onFailure(volleyError: VolleyError?, networkAPICallModel: NetworkAPICallModel?)
}
