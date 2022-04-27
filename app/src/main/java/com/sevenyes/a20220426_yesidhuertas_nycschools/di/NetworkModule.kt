package com.sevenyes.a20220426_yesidhuertas_nycschools.di

import com.sevenyes.a20220426_yesidhuertas_nycschools.network.ISchoolApiRepository
import com.sevenyes.a20220426_yesidhuertas_nycschools.network.SchoolAPI
import com.sevenyes.a20220426_yesidhuertas_nycschools.network.SchoolApiRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    fun providesHttpLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    @Provides
    fun providerOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .readTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
            .build()

    @Provides
    fun providerRetrofit(okHttpClient: OkHttpClient): SchoolAPI =
        Retrofit.Builder()
            .baseUrl(SchoolAPI.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(SchoolAPI::class.java)

    @Provides
    fun providesSchoolApiRepository(schoolAPI: SchoolAPI): ISchoolApiRepository =
        SchoolApiRepository(schoolAPI)

}

private const val REQUEST_TIMEOUT = 30L