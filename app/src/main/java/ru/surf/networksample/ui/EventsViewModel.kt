package ru.surf.networksample.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.surf.networksample.base.ScreenState
import ru.surf.networksample.interactor.EventsRepository
import javax.inject.Inject


class EventsViewModel @Inject constructor(
    eventsRepository: EventsRepository
) : ViewModel() {

    lateinit var eventsState: MutableLiveData<ScreenState<EventsState>>

    init {
        if (!::eventsState.isInitialized) {
            eventsState = eventsRepository.getEvents()
        }
    }
}