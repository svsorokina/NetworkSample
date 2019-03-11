package ru.surf.networksample.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.surf.networksample.base.ScreenState
import ru.surf.networksample.interactor.EventsRepository


class EventsViewModel : ViewModel() {

    private val eventsRepository: EventsRepository = EventsRepository.instance

    lateinit var eventsState: MutableLiveData<ScreenState<EventsState>>

    init {
        if (!::eventsState.isInitialized) {
            eventsState = eventsRepository.getEvents()
        }
    }
}

class EventsViewModelFactory : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return EventsViewModel() as T
    }
}