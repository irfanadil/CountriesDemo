package com.demo.jetpackdemoapp.di
/*
import com.demo.countriesdemo.network.CountriesApi
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class MainModule {

    @Provides
    @Singleton
    fun buildRetrofit(): Retrofit{
        return Retrofit
            .Builder()
            .baseUrl("https://gist.githubusercontent.com/")
            .addConverterFactory(
                GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun getClient(retrofit: Retrofit): CountriesApi{
        return retrofit.create(CountriesApi::class.java)
    }

}

 */