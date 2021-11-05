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

        binding.bConvert.setOnClickListener {
            mainViewModel.getConversionRates(
                binding.etAmount.text.toString(),
                binding.sFromCurrency.selectedItem.toString(),
                binding.sToCurrency.selectedItem.toString()
            )
        }

        mainViewModel.currency.observe(this, Observer {
            when (it) {
                is Resource.Success -> {
                    hideProgressBar()
                    binding.tvResult.text = it.message
                }
                is Resource.Error -> {
                    hideProgressBar()
                    binding.tvResult.text = "An Error occured: ${it.message}"

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