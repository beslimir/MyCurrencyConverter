package com.example.mycurrencyconverter.di

import com.example.mycurrencyconverter.data.api.CurrencyAPI
import com.example.mycurrencyconverter.repositories.MainRepository
import com.example.mycurrencyconverter.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun provideLoggingInterceptor() = HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.BODY)

    @Singleton
    @Provides
    fun provideOkHttpClient() = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor())
        .build()

    @Singleton
    @Provides
    fun provideRetrofitInstance(): CurrencyAPI = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(OkHttpClient())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(CurrencyAPI::class.java)

    @Singleton
    @Provides
    fun provideMainRepository(api: CurrencyAPI): MainRepository = MainRepository(api)


}