package ru.surf.networksample.network

import com.google.gson.annotations.SerializedName

interface ApiResponse

data class EventsResponse(
    @SerializedName("count")
    val count: Int,
    @SerializedName("results")
    val events: List<Event>
) : ApiResponse

data class Event(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String
)