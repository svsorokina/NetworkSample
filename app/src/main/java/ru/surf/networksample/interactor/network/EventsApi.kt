package ru.surf.networksample.interactor.network

import retrofit2.Call
import retrofit2.http.GET

const val BASE_URL = "https://kudago.com/public-api/v1.4/"

interface EventsApi {

    @GET("events")
    fun getEvents(): Call<EventsResponse>
}