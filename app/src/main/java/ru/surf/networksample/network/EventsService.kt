package ru.surf.networksample.network

import retrofit2.Call
import retrofit2.http.GET

const val BASE_URL = "https://kudago.com/public-api/v1.4/"

interface EventsService {

    @GET("events")
    fun getEvents(): Call<EventsResponse>
}