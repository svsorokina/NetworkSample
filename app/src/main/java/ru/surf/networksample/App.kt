package ru.surf.networksample

import android.app.Application
import ru.surf.networksample.di.AppComponent
import ru.surf.networksample.di.AppModule
import ru.surf.networksample.di.DaggerAppComponent


class App : Application() {

    val component: AppComponent by lazy {
        DaggerAppComponent
            .builder()
            .appModule(AppModule(this))
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        component.inject(this)
    }
}