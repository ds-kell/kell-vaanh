package kell.com.example.vaanh.network


//@Module
//@InstallIn(SingletonComponent::class)
//object RetrofitModule {
//    private const val BASE_URL = "http://localhost:8088/api/"
//
//    @Provides
//    @Singleton
//    fun provideRetrofit(): Retrofit {
//        return Retrofit.Builder()
//            .baseUrl(BASE_URL)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//    }
//
//    @Provides
//    @Singleton
//    fun provideApiService(retrofit: Retrofit): VaAnhApiService {
//        return retrofit.create(VaAnhApiService::class.java)
//    }
//}
