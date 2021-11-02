package com.example.mycurrencyconverter.data.api

import com.example.mycurrencyconverter.data.model.CurrencyResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CurrencyAPI {

    @GET("/v6/{API_KEY}/latest/{CURRENCY}")
    suspend fun getConversionRates(
        @Path("API_KEY") apiKey: String,
        @Path("CURRENCY") baseCurrency: String
    ): Response<CurrencyResponse>

}