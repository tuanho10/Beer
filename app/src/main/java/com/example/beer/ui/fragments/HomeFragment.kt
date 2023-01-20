package com.example.beer.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.beer.R
import com.example.beer.adapters.*
import com.example.beer.data.model.*
import com.example.beer.databinding.FragmentHomeBinding
import com.example.beer.util.Util
import com.example.beer.viewmodel.BaseViewModel

class HomeFragment : Fragment() {
    private lateinit var beerAdapter: BeerRecyclerAdapter
    private lateinit var util: Util
    lateinit var fragmentHomeBinding: FragmentHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fragmentHomeBinding = FragmentHomeBinding.inflate(layoutInflater)
        util = ViewModelProviders.of(this)[Util::class.java]
        beerAdapter = BeerRecyclerAdapter(util)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return fragmentHomeBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val baseViewModel = ViewModelProviders.of(this)[BaseViewModel::class.java]
        showLoadingCase()
        prepareCategoryRecyclerView()
        baseViewModel.observeBeers().observe(
            viewLifecycleOwner
        ) { i ->
            val beers = i!!.data
            setMealsByCategoryAdapter(beers)
            cancelLoadingCase()
        }
    }

    private fun showLoadingCase() {
        fragmentHomeBinding.apply {
            contraint.visibility = View.INVISIBLE
            loadingGif.visibility = View.VISIBLE
            rootHome.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.g_loading))
        }
    }

    private fun cancelLoadingCase() {
        fragmentHomeBinding.apply {
            contraint.visibility = View.VISIBLE
            loadingGif.visibility = View.INVISIBLE
            rootHome.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.white))
        }
    }

    private fun setMealsByCategoryAdapter(data: List<Data>) {
        beerAdapter.setDataBeers(data)
    }

    private fun prepareCategoryRecyclerView() {
        fragmentHomeBinding.recycleViewBeers.apply {
            adapter = beerAdapter
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        }
    }
}