package com.digitalhorizons.kidshistory.history

sealed class HistoryAction {

    data object ShowCarDetails : HistoryAction()
}