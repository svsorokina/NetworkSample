package ru.surf.networksample

import android.os.Handler
import android.os.Looper
import java.util.concurrent.Executor
import java.util.concurrent.Executors


class AppExecutors(val diskIO: Executor, val networkIO: Executor, val mainThread: Executor) {

    private object Holder { val INSTANCE = AppExecutors() }

    companion object {
        val instance: AppExecutors by lazy { Holder.INSTANCE }
    }

    constructor() : this(
            Executors.newSingleThreadExecutor(),
            Executors.newFixedThreadPool(3),
            MainThreadExecutor()
    )

    private class MainThreadExecutor : Executor {
        private val mainThreadHandler = Handler(Looper.getMainLooper())

        override fun execute(command: Runnable) {
            mainThreadHandler.post(command)
        }
    }
}