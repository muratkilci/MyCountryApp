package com.example.mycountryapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.mycountryapp.R
import com.example.mycountryapp.adapter.CountryAdapter
import com.example.mycountryapp.viewmodel.FeedViewModel
import kotlinx.android.synthetic.main.fragment_feed.*

class FeedFragment : Fragment() {
    private lateinit var viewModel: FeedViewModel
    private val countryAdapter = CountryAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_feed, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel = ViewModelProviders.of(this).get((FeedViewModel::class.java))
        viewModel.refreshData()


        CountryList.layoutManager = LinearLayoutManager(context)
        CountryList.adapter = countryAdapter

        swipeRefreshLayout.setOnRefreshListener {
            CountryList.visibility = View.GONE
            CountryError.visibility = View.GONE
            CountryLoading.visibility = View.VISIBLE
            viewModel.refreshFromAPI()
            swipeRefreshLayout.isRefreshing = false
        }

        observeLiveData()
    }

    private fun observeLiveData() {
        viewModel.countries.observe(viewLifecycleOwner, Observer { countries ->
            countries?.let {
                CountryList.visibility = View.VISIBLE
                countryAdapter.updateCountryList(countries)
            }

        })

        viewModel.countryError.observe(viewLifecycleOwner, Observer { error ->
            error?.let {
                if (it) {
                    CountryError.visibility = View.VISIBLE
                } else {
                    CountryError.visibility = View.GONE
                }
            }
        })

        viewModel.countryLoading.observe(viewLifecycleOwner, Observer { loading ->
            loading?.let {
                if (it) {
                    CountryLoading.visibility = View.VISIBLE
                    CountryList.visibility = View.GONE
                    CountryError.visibility = View.GONE
                } else {
                    CountryLoading.visibility = View.GONE
                }
            }
        })

    }
}