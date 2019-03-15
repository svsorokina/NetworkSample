package ru.surf.networksample.ui.di

import dagger.Component
import ru.surf.networksample.di.AppComponent
import ru.surf.networksample.di.PerScreen
import ru.surf.networksample.di.ViewModelFactoryModule
import ru.surf.networksample.ui.EventsActivity

@PerScreen
@Component(
    dependencies = [
        AppComponent::class
    ],
    modules = [
        ViewModelFactoryModule::class, EventsModule::class
    ]
)
interface EventsScreenComponent {
    fun inject(eventsActivity: EventsActivity)
}