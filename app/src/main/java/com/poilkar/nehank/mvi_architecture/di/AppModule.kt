package com.poilkar.nehank.mvi_architecture.di

import com.poilkar.nehank.mvi_architecture.data.api.ApiService
import com.poilkar.nehank.mvi_architecture.data.repository.QuotesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRetrofit() : ApiService {

        return Retrofit.Builder()
            .baseUrl("https://api.quotable.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    @Provides
    fun provideQuotesRepository(apiService: ApiService): QuotesRepository {
        return QuotesRepository(apiService)
    }


}