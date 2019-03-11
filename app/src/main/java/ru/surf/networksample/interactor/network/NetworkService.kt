package ru.surf.networksample.interactor.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.surf.networksample.BuildConfig


class NetworkService {

    private val okHttpClient: OkHttpClient
    private val retrofit: Retrofit
    val service: EventsService

    private object Holder { val INSTANCE = NetworkService() }

    companion object {
        val instance: NetworkService by lazy { Holder.INSTANCE }
    }

    init {
        okHttpClient = buildHttpClient()
        retrofit = buildRetrofit(okHttpClient)
        service = retrofit.create(EventsService::class.java)
    }

    private fun buildHttpClient(): OkHttpClient {
        val okHttpClientBuilder = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            okHttpClientBuilder.addInterceptor(interceptor)
        }
        return okHttpClientBuilder.build()
    }

    private fun buildRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }
}