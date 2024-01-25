package com.digitalhorizons.kidshistory.history

import android.graphics.Bitmap
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.content
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

internal class HistoryViewModel(
    private val generativeModel: GenerativeModel,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    private val _state = MutableStateFlow(HistoryState())
    private val _action = MutableSharedFlow<HistoryAction>()

    val state = _state.asStateFlow()
    val action = _action.asSharedFlow()

    fun reason(
        userInput: String,
        selectedImages: List<Bitmap>
    ) {
        viewModelScope.launch(dispatcher) {
            val prompt = "Look at the image(s), and then answer the following question: $userInput"

            val inputContent = content {
                for (bitmap in selectedImages) {
                    image(bitmap)
                }
                text(prompt)
            }
            generativeModel.generateContentStream("Quem descobriu o brasil")
                .onStart {
                    _state.update { it.onLoading() }
                }.catch { e ->
                    Log.d("Allef", "Error:${e.message} ")

                    _state.update { it.onError(e) }
                }.onCompletion {
                    Log.d("Allef", "onCompletion: ")
                    _state.update { it.onFinishedLoading() }
                }
                .collect { response ->
                    Log.d("Allef", "Sucesso:${response} ")

                   // _state.update { it.osResponseReceived(response) }
                }
        }

    }

    fun reasonText(
        userInput: String,
    ) {
        viewModelScope.launch(dispatcher) {
            val prompt = "Look at the image(s), and then answer the following question: $userInput"

            _state.update { it.onLoading() }

            val result = generativeModel.generateContent("Quem descobriu o brasil")
            Log.d("Allef", "reasonText: ${result.text}")
            _state.update { it.osResponseReceived(result.text.orEmpty()) }
        }

    }

    private fun sendAction(action: HistoryAction) = viewModelScope.launch(dispatcher) {
        _action.emit(action)
    }

}