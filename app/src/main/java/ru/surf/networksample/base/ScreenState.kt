package ru.surf.networksample.base

import ru.surf.networksample.exceptions.ApiException


sealed class ScreenState<out T> {
    object Loading : ScreenState<Nothing>()

    class Error(val apiException: ApiException) : ScreenState<Nothing>()

    class Render<T>(val renderState: T) : ScreenState<T>()
}