package kell.com.example.vaanh.interceptor

import kell.com.example.vaanh.utils.TokenManager
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class HeaderInterceptor @Inject constructor(tokenManager: TokenManager) :
    Interceptor {
    private val accessToken = tokenManager.fetchAuthToken()
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
        request.addHeader("Authorization", "Bearer $accessToken")
        return chain.proceed(request.build())
    }
}
