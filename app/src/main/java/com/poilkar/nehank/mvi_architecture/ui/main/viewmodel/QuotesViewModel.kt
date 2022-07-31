package com.poilkar.nehank.mvi_architecture.ui.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.poilkar.nehank.mvi_architecture.data.repository.QuotesRepository
import com.poilkar.nehank.mvi_architecture.ui.main.intent.QuotesIntent
import com.poilkar.nehank.mvi_architecture.ui.main.viewstate.QuotesViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuotesViewModel @Inject constructor(
    private val quotesRepository: QuotesRepository
) : ViewModel() {


    val  quotesIntent : Channel<QuotesIntent> = Channel(Channel.UNLIMITED)

    private val _quotesState = MutableStateFlow<QuotesViewState>(QuotesViewState.Idle)
    val quoteState : StateFlow<QuotesViewState>
    get() = _quotesState


    init {
        handleIntent()
    }

    fun handleIntent(){
        viewModelScope.launch {
            quotesIntent.consumeAsFlow().collect{
                when(it){
                    is QuotesIntent.GetQuotes -> fetchQuotes()
                }
            }
        }
    }

    private fun fetchQuotes(){
        viewModelScope.launch {
            _quotesState.value = QuotesViewState.Idle
            try{
                _quotesState.value = QuotesViewState.Success(quotesRepository.getQuotes())
            }catch (ex: Exception){
                _quotesState.value = QuotesViewState.Error(ex.message.toString())
            }
        }
    }

}