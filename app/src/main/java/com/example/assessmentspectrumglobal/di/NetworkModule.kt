package com.example.assessmentspectrumglobal.di

import com.example.assessmentspectrumglobal.dashboard.di.dashboardModule
import com.example.assessmentspectrumglobal.network.ApiHelper
import com.example.assessmentspectrumglobal.network.ApiHelperImpl
import com.example.assessmentspectrumglobal.network.ApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
Created by kiranb on 30/7/20
 */
val networkModule = module {
    single { provideOkHttpClient() }
    single { provideRetrofit(get()) }
    single { provideApiService(get()) }
    single<ApiHelper> {
        return@single ApiHelperImpl(get())
    }
}


private fun provideOkHttpClient(): OkHttpClient {
    val loggingInterceptor = HttpLoggingInterceptor()
    loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
    return OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()
}


private fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
    Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl("https://next.json-generator.com/api/")
        .client(okHttpClient)
        .build()

private fun provideApiService(retrofit: Retrofit): ApiService =
    retrofit.create(ApiService::class.java)
