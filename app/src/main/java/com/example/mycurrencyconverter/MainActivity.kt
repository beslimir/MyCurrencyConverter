package com.example.mycurrencyconverter

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mycurrencyconverter.databinding.ActivityMainBinding
import com.example.mycurrencyconverter.repositories.MainRepository
import com.example.mycurrencyconverter.util.Resource
import com.example.mycurrencyconverter.view_models.MainViewModel
import com.example.mycurrencyconverter.view_models.MainViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mainRepository = MainRepository()
        val mainViewModelFactory = MainViewModelFactory(mainRepository)
        mainViewModel = ViewModelProvider(this, mainViewModelFactory).get(MainViewModel::class.java)

        mainViewModel.getConversionRates("15", "EUR", "HRK")
        mainViewModel.currency.observe(this, Observer {
            when(it) {
                is Resource.Success -> {
                    hideProgressBar()
                    it.body?.let { currencyResponse ->
                        val conversionRates = currencyResponse.conversion_rates
                        val rate: Double? = mainViewModel.getRateForCurrency("HRK", conversionRates)
                        if (rate == null) {
                            binding.tvResult.text = "Currency not found"
                        } else {
                            val convertedCurrency = mainViewModel.calculateRate("15", rate)
                            binding.tvResult.text = "15 EUR = $convertedCurrency HRK"
                        }

                    }
                }
                is Resource.Error -> {
                    hideProgressBar()
                    it.message?.let { message ->
                        binding.tvResult.text = "An Error occured: $message"
                    }
                }
                is Resource.Loading -> {
                    showProgressBar()
                }
            }
        })

    }

    private fun showProgressBar() {
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        binding.progressBar.visibility = View.GONE
    }
}