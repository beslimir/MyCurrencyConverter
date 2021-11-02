package com.example.mycurrencyconverter.repositories

import com.example.mycurrencyconverter.data.api.RetrofitInstance

class MainRepository {

    suspend fun getConversionRates(apiKey: String, currency: String) =
        RetrofitInstance.api.getConversionRates(apiKey, currency)

}