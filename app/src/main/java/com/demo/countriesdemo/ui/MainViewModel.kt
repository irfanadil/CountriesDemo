package com.demo.jetpackdemoapp.ui.screens

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demo.countriesdemo.model.ApiResponse
import com.demo.countriesdemo.model.countries.CustomCountriesResponseItem
import com.demo.mybasiccompose.repo.CountriesRepo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch



class MainViewModel(val repo: CountriesRepo): ViewModel() {

    private val _countriesStateFlow = MutableStateFlow<ApiResponse<List<CustomCountriesResponseItem>>>(ApiResponse.Loading)
    val countriesFlow = _countriesStateFlow.asStateFlow()

    init {
        loadCountries()
    }

    fun loadCountries(){
        viewModelScope.launch(){
            repo.getAllCountries()
                .collect(){ it ->
                Log.e("viewModel", it.toString())
                 _countriesStateFlow.value =it
             }
        }
    }

}

