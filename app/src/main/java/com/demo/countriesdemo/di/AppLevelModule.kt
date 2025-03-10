package com.demo.countriesdemo.di

import android.content.Context
import com.demo.countriesdemo.network.CountriesApi
import com.demo.mybasiccompose.repo.CountriesRepo
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface AppLevelModule{
    val countriesApi:CountriesApi
    val repo: CountriesRepo
}

class AppLevelModuleImpl(): AppLevelModule {

    override val countriesApi: CountriesApi by lazy {
        Retrofit
            .Builder()
            .baseUrl("https://gist.githubusercontent.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(CountriesApi::class.java)
    }

    override val repo: CountriesRepo by lazy {
        CountriesRepo(countriesApi)
    }
}