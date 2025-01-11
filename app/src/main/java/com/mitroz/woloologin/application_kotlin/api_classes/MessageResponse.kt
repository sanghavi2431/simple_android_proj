package com.mitroz.woloologin.application_kotlin.api_classes

import com.google.gson.annotations.SerializedName

class MessageResponse {
    @SerializedName("message")
    var message: String = ""
}