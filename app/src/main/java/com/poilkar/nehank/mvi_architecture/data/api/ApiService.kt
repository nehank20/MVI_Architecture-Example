package com.poilkar.nehank.mvi_architecture.data.api

import com.poilkar.nehank.mvi_architecture.data.model.Quotes
import retrofit2.http.GET

interface ApiService {

    @GET("quotes")
    suspend fun getQuotes() : Quotes
}