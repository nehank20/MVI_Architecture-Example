package com.poilkar.nehank.mvi_architecture.data.repository

import com.poilkar.nehank.mvi_architecture.data.api.ApiService


class QuotesRepository(private val apiService: ApiService) {

    suspend fun getQuotes() = apiService.getQuotes()
}