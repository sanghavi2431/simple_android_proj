package com.mitroz.woloologin.application_kotlin.api_classes

import android.app.Activity

interface NetworkTimeOut {
    fun onTimeOutConnection(
        context: Activity?,
        networkAPICallModel: NetworkAPICallModel?,
        networkAPIResponseCallback: NetworkAPIResponseCallback?
    )
}
