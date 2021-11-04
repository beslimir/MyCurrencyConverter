package com.example.mycurrencyconverter.view_models

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mycurrencyconverter.data.model.ConversionRates
import com.example.mycurrencyconverter.data.model.CurrencyResponse
import com.example.mycurrencyconverter.repositories.MainRepository
import com.example.mycurrencyconverter.util.Constants.API_KEY
import com.example.mycurrencyconverter.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainRepository: MainRepository
) : ViewModel() {

    val currency: MutableLiveData<Resource<CurrencyResponse>> = MutableLiveData()

    fun getConversionRates(
        amount: String,
        fromCurrency: String,
        toCurrency: String
    ) = viewModelScope.launch {
        currency.postValue(Resource.Loading())
        try {
            val response = mainRepository.getConversionRates(API_KEY, fromCurrency)
            val result = response.body()

            if (response.isSuccessful && result != null) {
                currency.postValue(Resource.Success(result))
//            if (response.isSuccessful && result != null) {
//                val conversionRates = result.conversion_rates
//                val rate: Double? = getRateForCurrency(toCurrency, conversionRates)
//                if (rate == null) {
//                    currency.postValue(Resource.Error("Currency not found"))
//                } else {
//                    val convertedCurrency = calculateRate(amount, rate)
//                    currency.postValue(Resource.Success(result))
//                }
            } else {
                currency.postValue(Resource.Error(response.message()))
            }
        } catch (e: Exception) {
            currency.postValue(Resource.Error(e.message ?: "An error occurred"))
        }
    }

    fun calculateRate(amount: String, rate: Double): Double {
        return amount.toDouble() * rate
    }

    fun getRateForCurrency(toCurrency: String, conversionRates: ConversionRates) =
        when (toCurrency) {
//            "AED" -> conversionRates.AED
//            "AFN" -> conversionRates.AFN
//            "ALL" -> conversionRates.ALL
//            "AMD" -> conversionRates.AMD
//            "ANG" -> conversionRates.ANG
//            "AOA" -> conversionRates.AOA
//            "ARS" -> conversionRates.ARS
//            "AUD" -> conversionRates.AUD
//            "AWG" -> conversionRates.AWG
//            "AZN" -> conversionRates.AZN
            "BAM" -> conversionRates.BAM
//            "BBD" -> conversionRates.BBD
//            "BDT" -> conversionRates.BDT
//            "BGN" -> conversionRates.BGN
//            "BHD" -> conversionRates.BHD
//            "BIF" -> conversionRates.BIF
//            "BMD" -> conversionRates.BMD
//            "BND" -> conversionRates.BND
//            "BOB" -> conversionRates.BOB
//            "BRL" -> conversionRates.BRL
//            "BSD" -> conversionRates.BSD
//            "BTN" -> conversionRates.BTN
//            "BWP" -> conversionRates.BWP
//            "BYN" -> conversionRates.BYN
//            "BZD" -> conversionRates.BZD
            "CAD" -> conversionRates.CAD
//            "CDF" -> conversionRates.CDF
            "CHF" -> conversionRates.CHF
//            "CLP" -> conversionRates.CLP
//            "CNY" -> conversionRates.CNY
//            "COP" -> conversionRates.COP
//            "CRC" -> conversionRates.CRC
//            "CUC" -> conversionRates.CUC
//            "CUP" -> conversionRates.CUP
//            "CVE" -> conversionRates.CVE
//            "CZK" -> conversionRates.CZK
//            "DJF" -> conversionRates.DJF
//            "DKK" -> conversionRates.DKK
//            "DOP" -> conversionRates.DOP
//            "DZD" -> conversionRates.DZD
//            "EGP" -> conversionRates.EGP
//            "ERN" -> conversionRates.ERN
//            "ETB" -> conversionRates.ETB
            "EUR" -> conversionRates.EUR
//            "FJD" -> conversionRates.FJD
//            "FKP" -> conversionRates.FKP
//            "FOK" -> conversionRates.FOK
//            "GBP" -> conversionRates.GBP
//            "GEL" -> conversionRates.GEL
//            "GGP" -> conversionRates.GGP
//            "GHS" -> conversionRates.GHS
//            "GIP" -> conversionRates.GIP
//            "GMD" -> conversionRates.GMD
//            "GNF" -> conversionRates.GNF
//            "GTQ" -> conversionRates.GTQ
//            "GYD" -> conversionRates.GYD
//            "HKD" -> conversionRates.HKD
//            "HNL" -> conversionRates.HNL
            "HRK" -> conversionRates.HRK
//            "HTG" -> conversionRates.HTG
//            "HUF" -> conversionRates.HUF
//            "IDR" -> conversionRates.IDR
//            "ILS" -> conversionRates.ILS
//            "IMP" -> conversionRates.IMP
//            "INR" -> conversionRates.INR
//            "IQD" -> conversionRates.IQD
//            "IRR" -> conversionRates.IRR
//            "ISK" -> conversionRates.ISK
//            "JMD" -> conversionRates.JMD
//            "JOD" -> conversionRates.JOD
            "JPY" -> conversionRates.JPY
//            "KES" -> conversionRates.KES
//            "KGS" -> conversionRates.KGS
//            "KHR" -> conversionRates.KHR
//            "KID" -> conversionRates.KID
//            "KMF" -> conversionRates.KMF
//            "KRW" -> conversionRates.KRW
//            "KWD" -> conversionRates.KWD
//            "KYD" -> conversionRates.KYD
//            "KZT" -> conversionRates.KZT
//            "LAK" -> conversionRates.LAK
//            "LBP" -> conversionRates.LBP
//            "LKR" -> conversionRates.LKR
//            "LRD" -> conversionRates.LRD
//            "LSL" -> conversionRates.LSL
//            "LYD" -> conversionRates.LYD
//            "MAD" -> conversionRates.MAD
//            "MDL" -> conversionRates.MDL
//            "MGA" -> conversionRates.MGA
//            "MKD" -> conversionRates.MKD
//            "MMK" -> conversionRates.MMK
//            "MNT" -> conversionRates.MNT
//            "MOP" -> conversionRates.MOP
//            "MRU" -> conversionRates.MRU
//            "MUR" -> conversionRates.MUR
//            "MVR" -> conversionRates.MVR
//            "MWK" -> conversionRates.MWK
//            "MXN" -> conversionRates.MXN
//            "MYR" -> conversionRates.MYR
//            "MZN" -> conversionRates.MZN
//            "NAD" -> conversionRates.NAD
//            "NGN" -> conversionRates.NGN
//            "NIO" -> conversionRates.NIO
//            "NOK" -> conversionRates.NOK
//            "NPR" -> conversionRates.NPR
//            "NZD" -> conversionRates.NZD
//            "OMR" -> conversionRates.OMR
//            "PAB" -> conversionRates.PAB
//            "PEN" -> conversionRates.PEN
//            "PGK" -> conversionRates.PGK
//            "PHP" -> conversionRates.PHP
//            "PKR" -> conversionRates.PKR
//            "PLN" -> conversionRates.PLN
//            "PYG" -> conversionRates.PYG
//            "QAR" -> conversionRates.QAR
//            "RON" -> conversionRates.RON
//            "RSD" -> conversionRates.RSD
//            "RUB" -> conversionRates.RUB
//            "RWF" -> conversionRates.RWF
//            "SAR" -> conversionRates.SAR
//            "SBD" -> conversionRates.SBD
//            "SCR" -> conversionRates.SCR
//            "SDG" -> conversionRates.SDG
//            "SEK" -> conversionRates.SEK
//            "SGD" -> conversionRates.SGD
//            "SHP" -> conversionRates.SHP
//            "SLL" -> conversionRates.SLL
//            "SOS" -> conversionRates.SOS
//            "SRD" -> conversionRates.SRD
//            "SSP" -> conversionRates.SSP
//            "STN" -> conversionRates.STN
//            "SYP" -> conversionRates.SYP
//            "SZL" -> conversionRates.SZL
//            "THB" -> conversionRates.THB
//            "TJS" -> conversionRates.TJS
//            "TMT" -> conversionRates.TMT
//            "TND" -> conversionRates.TND
//            "TOP" -> conversionRates.TOP
//            "TRY" -> conversionRates.TRY
//            "TTD" -> conversionRates.TTD
//            "TVD" -> conversionRates.TVD
//            "TWD" -> conversionRates.TWD
//            "TZS" -> conversionRates.TZS
//            "UAH" -> conversionRates.UAH
//            "UGX" -> conversionRates.UGX
            "USD" -> conversionRates.USD
//            "UYU" -> conversionRates.UYU
//            "UZS" -> conversionRates.UZS
//            "VES" -> conversionRates.VES
//            "VND" -> conversionRates.VND
//            "VUV" -> conversionRates.VUV
//            "WST" -> conversionRates.WST
//            "XAF" -> conversionRates.XAF
//            "XCD" -> conversionRates.XCD
//            "XDR" -> conversionRates.XDR
//            "XOF" -> conversionRates.XOF
//            "XPF" -> conversionRates.XPF
//            "YER" -> conversionRates.YER
//            "ZAR" -> conversionRates.ZAR
//            "ZMW" -> conversionRates.ZMW
            else -> null
        }
}