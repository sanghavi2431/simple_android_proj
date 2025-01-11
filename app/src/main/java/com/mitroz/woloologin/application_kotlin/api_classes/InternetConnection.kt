package com.mitroz.woloologin.application_kotlin.api_classes

import android.app.Activity
import com.mitroz.woloologin.application_kotlin.api_classes.NetworkAPICallModel
import com.mitroz.woloologin.application_kotlin.api_classes.NetworkAPIResponseCallback

interface InternetConnection {
    fun onNoInternetConnection(
        context: Activity?,
        networkAPICallModel: NetworkAPICallModel?,
        networkAPIResponseCallback: NetworkAPIResponseCallback?
    )
}
