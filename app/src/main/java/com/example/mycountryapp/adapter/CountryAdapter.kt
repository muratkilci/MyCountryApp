package com.example.mycountryapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.mycountryapp.R
import com.example.mycountryapp.databinding.ItemCountryBinding
import com.example.mycountryapp.model.Country
import com.example.mycountryapp.util.downloadFromUrl
import com.example.mycountryapp.util.placeholderProgressBar
import com.example.mycountryapp.view.FeedFragmentDirections
import kotlinx.android.synthetic.main.item_country.view.*

class CountryAdapter(val countryList: ArrayList<Country>) :
    RecyclerView.Adapter<CountryAdapter.CountryViewHolder>(), CountryClickListener {

    class CountryViewHolder(var view: ItemCountryBinding) : RecyclerView.ViewHolder(view.root) {


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        // val view = inflater.inflate(R.layout.item_country, parent, false)
        val view=DataBindingUtil.inflate<ItemCountryBinding>(inflater,R.layout.item_country,parent,false)
        return CountryViewHolder(view)
    }

    override fun getItemCount(): Int {
        return countryList.size
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.view.country = countryList[position]
        holder.view.listener = this


        /*
        holder.view.name.text = countryList[position].CountryName
        holder.view.region.text = countryList[position].CountryRegion

        holder.view.setOnClickListener {
            val action = FeedFragmentDirections.actionFeedFragmentToCountryFragment()
            action.countryUuid = countryList[position].uuid
            Navigation.findNavController(it).navigate(action)
        }

        holder.view.imageView.downloadFromUrl(
            countryList[position].imageUrl,
            placeholderProgressBar(holder.view.context)
        )
*/
    }

    fun updateCountryList(newCountryList: List<Country>) {
        countryList.clear()
        countryList.addAll(newCountryList)
        notifyDataSetChanged()
    }

    override fun onCountryClicked(v: View) {
        val uuid =v.countryUuid.text.toString().toInt()
        val action = FeedFragmentDirections.actionFeedFragmentToCountryFragment()
        action.countryUuid = uuid
        Navigation.findNavController(v).navigate(action)    }


}