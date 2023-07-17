package com.example.fitnesskit.di

import com.example.fitnesskit.api.ApiService
import com.example.fitnesskit.api.RetrofitClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn (SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideRetrofit(base_url: String): Retrofit{
        return Retrofit.Builder()
            .baseUrl(base_url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideBaseUrl(): String{
        return RetrofitClient.BASE_URL
    }

    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService{
        return retrofit.create(ApiService::class.java)
    }

    //todo fun to provide repo, inject dependens
}