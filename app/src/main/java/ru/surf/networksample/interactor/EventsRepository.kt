package ru.surf.networksample.interactor

import androidx.lifecycle.MutableLiveData
import ru.surf.networksample.base.ScreenState
import ru.surf.networksample.exceptions.ApiException
import ru.surf.networksample.interactor.network.NetworkService
import ru.surf.networksample.runOnNetworkIO
import ru.surf.networksample.runOnUi
import ru.surf.networksample.ui.EventsState
import java.io.IOException


class EventsRepository {

    private object Holder {
        val INSTANCE = EventsRepository()
    }

    companion object {
        val instance: EventsRepository by lazy { Holder.INSTANCE }
    }

    fun getEvents(): MutableLiveData<ScreenState<EventsState>> {

        val mutableLiveData = MutableLiveData<ScreenState<EventsState>>()
        mutableLiveData.value = ScreenState.Loading

        runOnNetworkIO {

            try {
                val eventsResponse = NetworkService.instance.service.getEvents().execute()

                runOnUi {

                    if (eventsResponse.isSuccessful) {
                        val events = eventsResponse.body()

                        if (events != null) {
                            mutableLiveData.value = ScreenState.Render(EventsState.EventsLoaded(events.transform().events))
                        } else {
                            mutableLiveData.value = ScreenState.Error(ApiException(0, "Getting events error"))
                        }
                    } else {
                        mutableLiveData.value = ScreenState.Error(ApiException(eventsResponse.code(), "Getting events error"))
                    }
                }
            } catch (ioException: IOException) {
                runOnUi { mutableLiveData.value = ScreenState.Error(ApiException(0, "Getting events error")) }
            }
        }

        return mutableLiveData
    }
}