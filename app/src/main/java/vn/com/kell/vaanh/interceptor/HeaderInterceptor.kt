package vn.com.kell.vaanh.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import vn.com.kell.vaanh.utils.TokenManager
import javax.inject.Inject

class HeaderInterceptor @Inject constructor(tokenManager: TokenManager) :
    Interceptor {
    private var accessToken = tokenManager.fetchAuthToken()
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
        accessToken = ""
        if (accessToken == "") {
            return chain.proceed(request.build())
        }
        request.addHeader("Authorization", "Bearer $accessToken")
        return chain.proceed(request.build())
    }
}
