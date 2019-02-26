package ru.surf.networksample.network


interface ResponseCallback<R : ApiResponse> {
    fun onSuccess(apiResponse: R)
    fun onFailure(errorMessage: String)
}