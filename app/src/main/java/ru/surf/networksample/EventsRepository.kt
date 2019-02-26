package ru.surf.networksample

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.surf.networksample.network.EventsResponse
import ru.surf.networksample.network.NetworkService
import ru.surf.networksample.network.ResponseCallback


class EventsRepository {
    private object Holder { val INSTANCE = EventsRepository() }

    companion object {
        val instance: EventsRepository by lazy { Holder.INSTANCE }
    }

    fun getEvents(responseCallback: ResponseCallback<EventsResponse>) {
        //retrofit async
        NetworkService.instance.service.getEvents().enqueue(object : Callback<EventsResponse> {
            override fun onFailure(call: Call<EventsResponse>, t: Throwable) {
                responseCallback.onFailure("Getting events error")
            }

            override fun onResponse(call: Call<EventsResponse>, response: Response<EventsResponse>) {
                val eventsResponse = response.body()
                if (eventsResponse != null) {
                    responseCallback.onSuccess(eventsResponse)
                } else {
                    responseCallback.onFailure("Getting events error")
                }
            }
        })

        //executors
        /*
        AppExecutors.instance.networkIO.execute {

            val eventsResponse = NetworkService.instance.service.getEvents().execute()
            if (eventsResponse.isSuccessful) {
                val events = eventsResponse.body()

                runOnUi {
                    if (events != null) {
                        responseCallback.onSuccess(events)
                    } else {
                        responseCallback.onFailure("Getting events error")
                    }
                }
            } else {
                runOnUi { responseCallback.onFailure("Getting events error") }
            }
        }
        */
    }

    private fun runOnUi(block: () -> Unit) {
        AppExecutors.instance.mainThread.execute {
            block.invoke()
        }
    }
}