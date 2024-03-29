package com.example.mycountryapp.service

import com.example.mycountryapp.model.Country
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class CountryAPIService {

    //https://raw.githubusercontent.com/atilsamancioglu/IA19-DataSetCountries/master/countrydataset.json
    //BASE URL->https://raw.githubusercontent.com/
    //EXT ->atilsamancioglu/IA19-DataSetCountries/master/countrydataset.json

    private val BASE_URL = "https://raw.githubusercontent.com/"
    private val api= Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())         //SerializedName is used to change the name of the Gson key
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(CountryAPI::class.java)

    fun getData():Single<List<Country>>{
        return api.getCountries()
    }
}