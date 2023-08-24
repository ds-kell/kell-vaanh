package kell.com.example.vaanh.interceptor

import kell.com.example.vaanh.utils.SessionManager
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class HeaderInterceptor @Inject constructor(sessionManager: SessionManager) :
    Interceptor {
    private val accessToken = sessionManager.fetchAuthToken()
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
        request.addHeader("Authorization", "Bearer $accessToken")
        return chain.proceed(request.build())
    }
}
//class HeaderInterceptor @Inject constructor(private val sessionManager: SessionManager) :
//    Interceptor {
//    private val accessToken = sessionManager.fetchAuthToken()
//
//    override fun intercept(chain: Interceptor.Chain): Response {
//        var request = chain.request()
//        if (request.header("Authorization") == null) {
//            if (accessToken?.isNotEmpty() == true) {
//                request =
//                    request.newBuilder().header("Authorization", "Bearer $accessToken").build()
//            }
//        }
//        return chain.proceed(request)
//    }
//}