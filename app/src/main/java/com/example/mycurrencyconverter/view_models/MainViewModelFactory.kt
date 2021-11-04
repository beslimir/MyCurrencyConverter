package com.example.mycurrencyconverter.view_models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mycurrencyconverter.repositories.MainRepository

/**
 * Not needed because of Dagger Hilt
 * **/

class MainViewModelFactory(val mainRepository: MainRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(mainRepository) as T
    }
}