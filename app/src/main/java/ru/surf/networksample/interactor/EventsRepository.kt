package ru.surf.networksample.interactor

import androidx.lifecycle.MutableLiveData
import ru.surf.networksample.base.ScreenState
import ru.surf.networksample.exceptions.ApiException
import ru.surf.networksample.interactor.network.EventsApi
import ru.surf.networksample.runOnNetworkIO
import ru.surf.networksample.runOnUi
import ru.surf.networksample.ui.EventsState
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EventsRepository @Inject constructor(
    private val eventsApi: EventsApi
) {

    fun getEvents(): MutableLiveData<ScreenState<EventsState>> {

        val mutableLiveData = MutableLiveData<ScreenState<EventsState>>()
        mutableLiveData.value = ScreenState.Loading

        runOnNetworkIO {

            try {
                val eventsResponse = eventsApi.getEvents().execute()

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