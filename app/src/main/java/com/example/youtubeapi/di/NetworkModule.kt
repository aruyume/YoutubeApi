package com.example.youtubeapi.di

import com.example.youtubeapi.data.network.api.YoutubeApiService
import com.example.youtubeapi.utils.Constants.BASE_URL
import com.example.youtubeapi.utils.Constants.TIMEOUT_DURATION
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {

    single { provideRetrofit(get()) }

    single { provideApiService(get()) }

    single { provideOkHttpClient(get()) }

    single { provideLoggingInterceptor() }
}

fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
    Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

fun provideApiService(retrofit: Retrofit): YoutubeApiService =
    retrofit.create(YoutubeApiService::class.java)

fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
    OkHttpClient.Builder()
        .readTimeout(TIMEOUT_DURATION, TimeUnit.SECONDS)
        .writeTimeout(TIMEOUT_DURATION, TimeUnit.SECONDS)
        .connectTimeout(TIMEOUT_DURATION, TimeUnit.SECONDS)
        .addInterceptor(loggingInterceptor)
        .build()

fun provideLoggingInterceptor(): HttpLoggingInterceptor =
    HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }