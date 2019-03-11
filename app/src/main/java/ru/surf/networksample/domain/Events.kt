package ru.surf.networksample.domain


data class Events(
    val count: Int,
    val events: List<Event>
)

data class Event(
    val id: Int,
    val title: String
)