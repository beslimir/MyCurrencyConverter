package com.example.mycurrencyconverter.repositories

import com.example.mycurrencyconverter.data.api.CurrencyAPI
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val api: CurrencyAPI
) {

    suspend fun getConversionRates(apiKey: String, currency: String) = api.getConversionRates(apiKey, currency)

}