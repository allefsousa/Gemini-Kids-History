package com.digitalhorizons.kidshistory.history

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.digitalhorizons.common.binding.viewBinding
import com.digitalhorizons.kidshistory.common.observeFlow
import com.digitalhorizons.kidshistory.databinding.ActivityHistoryBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

internal class HistoryActivity : AppCompatActivity() {

    private val viewModel: HistoryViewModel by viewModel()
    private val binding: ActivityHistoryBinding by viewBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        viewModel.reasonText("")
        observeViewModelFlows()
    }

    private fun observeViewModelFlows() = with(viewModel) {
        observeFlow(state, ::onStateChanged)
        observeFlow(action, ::onAction)
    }

    private fun onStateChanged(state: HistoryState) = with(state) {
        binding.progressBar.isVisible = isLoading
        successResponse?.let {
            binding.tvHistory.isVisible = true
            binding.tvHistory.text = it
        }
    }

    private fun onAction(action: HistoryAction) = when (action) {
        is HistoryAction.ShowCarDetails -> {}
    }
}