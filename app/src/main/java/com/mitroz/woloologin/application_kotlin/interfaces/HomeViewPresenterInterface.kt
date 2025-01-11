package `in`.woloo.www.application_kotlin.interfaces

import `in`.woloo.www.application_kotlin.model.server_response.AuthConfigResponse
import com.mitroz.woloologin.application_kotlin.api_classes.NetworkAPICallModel

interface HomeViewPresenterInterface {

    fun authConfigSuccess(authConfigResponse: AuthConfigResponse?)
}
