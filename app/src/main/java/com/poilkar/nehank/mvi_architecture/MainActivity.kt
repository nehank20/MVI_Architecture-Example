package com.poilkar.nehank.mvi_architecture

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.poilkar.nehank.mvi_architecture.databinding.ActivityMainBinding
import com.poilkar.nehank.mvi_architecture.ui.main.adapter.QuotesAdapter
import com.poilkar.nehank.mvi_architecture.ui.main.intent.QuotesIntent
import com.poilkar.nehank.mvi_architecture.ui.main.viewmodel.QuotesViewModel
import com.poilkar.nehank.mvi_architecture.ui.main.viewstate.QuotesViewState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding: ActivityMainBinding
        get() = _binding!!

    private val viewModel: QuotesViewModel by viewModels()
    private val mainAdapter = QuotesAdapter()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()

        observeViewModels()

        lifecycleScope.launch {
            viewModel.quotesIntent.send(QuotesIntent.GetQuotes)
        }

    }

    private fun observeViewModels() {
        lifecycleScope.launch {
            viewModel.quoteState.collect {
                when (it) {
                    is QuotesViewState.Loading -> {
                        binding.progress.visibility = View.VISIBLE
                    }
                    is QuotesViewState.Success -> {
                        binding.progress.visibility = View.GONE
                        mainAdapter.addItems(it.data.results)
                    }
                    is QuotesViewState.Error -> {
                        binding.progress.visibility = View.GONE
                    }

                }

            }
        }

    }

    private fun initView() {
        binding.rvPosts.adapter = mainAdapter
    }
}