package ru.surf.networksample.interactor.network

import com.google.gson.annotations.SerializedName
import ru.surf.networksample.domain.Event
import ru.surf.networksample.domain.Events

interface ApiResponse

data class EventsResponse(
    @SerializedName("count")
    val count: Int,
    @SerializedName("results")
    val eventsObj: List<EventObj>
) : ApiResponse {

    fun transform(): Events {
        val transformedEvents: MutableList<Event> = mutableListOf()

        eventsObj.forEach {
            transformedEvents.add(it.transform())
        }

        return Events(
            count,
            transformedEvents
        )
    }
}

data class EventObj(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String
) {
    fun transform(): Event {
        return Event(id, title)
    }
}