package com.example.mycountryapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Country(
    @ColumnInfo(name = "name")
    @SerializedName("name")
    val CountryName: String?,

    @ColumnInfo(name = "region")
    @SerializedName("region")
    val CountryRegion: String?,

    @ColumnInfo(name = "capital")
    @SerializedName("capital")
    val CountryCapital: String?,

    @ColumnInfo(name = "currency")
    @SerializedName("currency")
    val CountryCurrency: String?,

    @ColumnInfo(name = "language")
    @SerializedName("language")
    val CountryLanguage: String?,

    @ColumnInfo(name = "flag")
    @SerializedName("flag")
    val imageUrl: String?
){
    @PrimaryKey(autoGenerate = true)
    var uuid: Int = 0
}
//   @SerializedName("...")  clastaki datanın adını API içerisinden gelecek olan data adı ile eşlenir. Eğer API data adı ile clastaki dataların isimleri aynı olur ise bu işleme gerek olmaz.