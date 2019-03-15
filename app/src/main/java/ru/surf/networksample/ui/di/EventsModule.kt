package ru.surf.networksample.ui.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ru.surf.networksample.di.PerScreen
import ru.surf.networksample.di.ViewModelKey
import ru.surf.networksample.ui.EventsViewModel

@Module
abstract class EventsModule {

    @Binds
    @IntoMap
    @ViewModelKey(EventsViewModel::class)
    @PerScreen
    abstract fun bindEventsViewModel(eventsViewModel: EventsViewModel): ViewModel
}