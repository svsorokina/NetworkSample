package ru.surf.networksample.di

import dagger.Component
import ru.surf.networksample.App
import ru.surf.networksample.base.DaggerViewModelsFactory
import ru.surf.networksample.interactor.EventsRepository
import ru.surf.networksample.interactor.network.EventsApi
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, NetworkModule::class])
interface AppComponent {
    fun inject(app: App)

    fun eventsApi(): EventsApi
    fun eventsRepository(): EventsRepository
}