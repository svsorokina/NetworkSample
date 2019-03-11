package ru.surf.networksample.ui

import ru.surf.networksample.domain.Event


sealed class EventsState {
    class EventsLoaded(val events: List<Event>) : EventsState()
}