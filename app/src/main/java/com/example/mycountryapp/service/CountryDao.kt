package com.example.mycountryapp.service

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.mycountryapp.model.Country

@Dao
interface CountryDao {

    @Insert
    suspend fun insertAll(vararg country: Country): List<Long>

    //Insert -> Veritabanına verilen değerlerle yeni bir satır ekler.
    //suspend -> coroutine kullanımı için eklenen kısım.(pause-resume)
    //vararg -> birden fazla değer alabilmek için kullanılır.(sayısı belli olmayan istediğimiz kadar obje verilebiliyor)
    //List<Long> -> veritabanına eklenen satırların id'si döndürür.
    //Query -> veritabanından veri çekmek için kullanılır.


    //Tüm ülkeleri çekmek için kullanılır.
    @Query("SELECT * FROM country")
    suspend fun getAllCountries(): List<Country>

    //Belirli bir ülkeyi çekmek için kullanılır.
    @Query("SELECT * FROM country WHERE uuid = :countryId")
    suspend fun getCountry(countryId: Int): Country

    //Tüm ülkeleri silmek için kullanılır.
    @Query("DELETE FROM country")
    suspend fun deleteAllCountries()
/*
    //Belirli bir ülkeyi silmek için kullanılır.
    @Query("DELETE FROM country WHERE uuid = :countryId")
    suspend fun deleteCountry(countryId: Int)*/
}