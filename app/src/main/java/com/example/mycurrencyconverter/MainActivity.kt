package com.example.mycurrencyconverter

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.mycurrencyconverter.databinding.ActivityMainBinding
import com.example.mycurrencyconverter.util.Resource
import com.example.mycurrencyconverter.view_models.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //TODO: When issue with no-args is solved, refresh some parts of app...

        /** No longer needed because of Dagger Hilt **/
//        val mainRepository = MainRepository()
//        val mainViewModelFactory = MainViewModelFactory(mainRepository)
//        mainViewModel = ViewModelProvider(this, mainViewModelFactory).get(MainViewModel::class.java)

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