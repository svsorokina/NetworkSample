package ru.surf.networksample.di

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import ru.surf.networksample.base.DaggerViewModelsFactory
import javax.inject.Singleton


@Module
abstract class ViewModelFactoryModule {

    @Binds
    @Singleton
    abstract fun bindViewModelFactory(viewModelFactory: DaggerViewModelsFactory): ViewModelProvider.Factory
}