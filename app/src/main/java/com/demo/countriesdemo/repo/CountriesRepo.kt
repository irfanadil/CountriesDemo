package com.demo.mybasiccompose.repo

import com.demo.countriesdemo.model.ApiResponse
import com.demo.countriesdemo.model.countries.CustomCountriesResponseItem
import com.demo.countriesdemo.network.CountriesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class CountriesRepo (val countriesApi: CountriesApi) {

    fun getAllCountries() = flow {
        try {
            val response = countriesApi.getCountries().map { dto ->
                CustomCountriesResponseItem(
                    capital = dto.capital,
                    code = dto.code,
                    currency = dto.currency,
                    demonym = dto.demonym,
                    flag = dto.flag,
                    language = dto.language,
                    name = dto.name,
                    region = dto.region
                )
            }
            emit(ApiResponse.Success(response))
        }
        catch (e: Exception){ emit(ApiResponse.Error("Error ="+e.message.toString(), e)) }
    }.flowOn(Dispatchers.IO) // for background processing...

}