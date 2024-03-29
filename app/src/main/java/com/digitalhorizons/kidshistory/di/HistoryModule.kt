package com.digitalhorizons.kidshistory.di

import com.digitalhorizons.kidshistory.history.HistoryViewModel
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.generationConfig
import org.koin.androidx.viewmodel.dsl.viewModel
import com.digitalhorizons.kidshistory.BuildConfig
import org.koin.dsl.module

val config = generationConfig {
    temperature = 0.7f
}

val generativeModel = GenerativeModel(
    modelName = "gemini-pro",
    apiKey = BuildConfig.apiKey,
    generationConfig = config
)
val viewModelModule = module {
    viewModel { HistoryViewModel(generativeModel = generativeModel) }
}