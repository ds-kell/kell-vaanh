package kell.com.example.vaanh.interceptor

import okhttp3.Interceptor

class HeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
        var request = chain.request()
        request = request.newBuilder().header("Authorization", "$ ").build()

        return chain.proceed(request)
    }
}