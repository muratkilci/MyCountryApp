package com.example.mycountryapp.service

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mycountryapp.model.Country

@Database(entities = [Country::class], version = 1)
abstract class CountryDatabase : RoomDatabase() {

    abstract fun countryDao(): CountryDao

    //Singleton
    //volatile - tüm treadleri görünür yapar.


    companion object {
        @Volatile
        private var instance: CountryDatabase? = null

        private val lock = Any()

        operator fun invoke(context: Context)=instance?:synchronized(lock){
            instance?:makeDatabase(context).also {
                instance = it
            }
        }

        private fun makeDatabase(context:Context) = Room.databaseBuilder(
            context.applicationContext,CountryDatabase::class.java,"countrydatabase"
        ).build()


    }






}