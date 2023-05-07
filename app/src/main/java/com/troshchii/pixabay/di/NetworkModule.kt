package com.troshchii.pixabay.di

import com.troshchii.pixabay.network.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Named("baseUrl")
    @Provides
    fun provideBaseUrl() = "https://pixabay.com/api/"

    @Provides
    fun provideApiService(@Named("baseUrl") baseUrl: String): ApiService {
        val retrofit =
            Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create())
                .build()

        return retrofit.create(ApiService::class.java)
    }
}
