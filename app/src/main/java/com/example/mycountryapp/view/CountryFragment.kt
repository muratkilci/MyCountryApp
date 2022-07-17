package com.example.mycountryapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mycountryapp.R
import com.example.mycountryapp.databinding.FragmentCountryBinding
import com.example.mycountryapp.model.Country
import com.example.mycountryapp.util.downloadFromUrl
import com.example.mycountryapp.util.placeholderProgressBar
import com.example.mycountryapp.viewmodel.CountryViewModel
import com.example.mycountryapp.viewmodel.FeedViewModel
import kotlinx.android.synthetic.main.fragment_country.*


class CountryFragment : Fragment() {
    private var countryUuid = 0
    private lateinit var viewModel: CountryViewModel
    private lateinit var dataBinding: FragmentCountryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_country, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            countryUuid = CountryFragmentArgs.fromBundle(it).countryUuid
        }

        viewModel = ViewModelProvider(this).get(CountryViewModel::class.java)
        viewModel.getDataFromRoom(countryUuid)

        observeLiveData()
    }

    private fun observeLiveData() {
        viewModel.countryLiveData.observe(viewLifecycleOwner, Observer { country ->
            country?.let {
                dataBinding.selectedCountry = country
                /*
                countryName.text = country.CountryName
                countryCapital.text = country.CountryCapital
                countryCurrency.text = country.CountryCurrency
                countryLanguage.text = country.CountryLanguage
                countryRegion.text = country.CountryRegion
                context?.let {
                    countryImage.downloadFromUrl(country.imageUrl, placeholderProgressBar(it))

                }
               */
            }
        })
    }
}