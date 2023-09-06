package vn.com.kell.vaanh.injection.module

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import vn.com.kell.vaanh.interceptor.HeaderInterceptor
import vn.com.kell.vaanh.repository.VaAnhRepository
import vn.com.kell.vaanh.repository.impl.VaAnhRepositoryImpl
import vn.com.kell.vaanh.repository.service.VaAnhApiService
import vn.com.kell.vaanh.utils.TokenManager
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object VaAnhModule {
    private const val BASE_URL = "http://10.0.2.2:8088/api/"

    @Singleton
    @Provides
    fun provideOkHttpClient(
        headerInterceptor: HeaderInterceptor,
    ): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(headerInterceptor)
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        .build()

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .build()

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): VaAnhApiService =
        retrofit.create(VaAnhApiService::class.java)

    @Singleton
    @Provides
    fun provideVaAnhRepository(apiService: VaAnhApiService): VaAnhRepository {
        return VaAnhRepositoryImpl(apiService)
    }

    @Singleton
    @Provides
    fun provideTokenManager(@ApplicationContext context: Context): TokenManager =
        TokenManager(context)

    @Singleton
    @Provides
    fun provideHeaderInterceptor(tokenManager: TokenManager): HeaderInterceptor =
        HeaderInterceptor(tokenManager)
}
