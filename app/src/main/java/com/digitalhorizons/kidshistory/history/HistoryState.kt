package com.digitalhorizons.kidshistory.history



data class HistoryState(
    val isLoading: Boolean = false,
    val error: Throwable? = null,
    val successResponse: String? = null
) {

    fun onLoading() = copy(isLoading = true)
    fun onFinishedLoading() = copy(isLoading = false)
    fun onError(e: Throwable) = copy(isLoading = false, error = e)
    fun osResponseReceived(response: String) =
        copy(isLoading = false, successResponse = response)

}