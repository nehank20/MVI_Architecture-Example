package com.poilkar.nehank.mvi_architecture.ui.main.viewstate

import com.poilkar.nehank.mvi_architecture.data.model.Quotes

sealed class QuotesViewState{
    object Idle : QuotesViewState()
    object Loading : QuotesViewState()
    class Error(val message : String) : QuotesViewState()
    class Success(val data : Quotes) : QuotesViewState()
}
