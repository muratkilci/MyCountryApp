package com.example.mycountryapp.service

import com.example.mycountryapp.model.Country
import io.reactivex.Single
import retrofit2.http.GET

interface CountryAPI {

    //GET veri çekme
    //POST veri değitirme
    //Single sadece bir kere çeker
    //Observable verileri devamlı çeker

    //https://raw.githubusercontent.com/atilsamancioglu/IA19-DataSetCountries/master/countrydataset.json
    //BASE URL->https://raw.githubusercontent.com/
    //EXT ->atilsamancioglu/IA19-DataSetCountries/master/countrydataset.json


    @GET("atilsamancioglu/IA19-DataSetCountries/master/countrydataset.json")
    fun getCountries():Single< List<Country>>


}