package com.example.beer.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.beer.R
import com.example.beer.adapters.FavoriteBeerRecyclerAdapter
import com.example.beer.databinding.FragmentFavoriteBeersBinding
import com.example.beer.util.Util


class FavoriteMeals : Fragment() {
    lateinit var recyclerView: RecyclerView
    lateinit var fragmentFavoriteBeersBinding: FragmentFavoriteBeersBinding
    private lateinit var favoriteBeerAdapter: FavoriteBeerRecyclerAdapter
    private lateinit var util: Util

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        util = ViewModelProviders.of(this)[Util::class.java]
        favoriteBeerAdapter = FavoriteBeerRecyclerAdapter(util)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentFavoriteBeersBinding =
            FragmentFavoriteBeersBinding.inflate(inflater, container, false)
        return fragmentFavoriteBeersBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapterFavoriteBeer(view)
        util.observeSaveBeer().observe(
            viewLifecycleOwner
        ) { data ->
            favoriteBeerAdapter.setFavoriteBeers(data!!)
            if (data.isEmpty())
                fragmentFavoriteBeersBinding.txtEmpty.visibility = View.VISIBLE
            else
                fragmentFavoriteBeersBinding.txtEmpty.visibility = View.GONE
        }
    }

    private fun initAdapterFavoriteBeer(v: View) {
        recyclerView = v.findViewById<RecyclerView>(R.id.recycleViewFavorite)
        recyclerView.adapter = favoriteBeerAdapter
        recyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
    }
}