package com.example.beer.ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.beer.binder.FavoriteItemBinder
import com.example.beer.data.model.DataDB
import com.example.beer.databinding.FragmentFavoriteBeersBinding
import com.example.beer.util.Util
import mva2.adapter.ListSection
import mva2.adapter.MultiViewAdapter


class FavoriteBeersFragment : Fragment() {
    lateinit var fragmentFavoriteBeersBinding: FragmentFavoriteBeersBinding
    private lateinit var multiViewAdapter: MultiViewAdapter
    private lateinit var util: Util

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        util = ViewModelProviders.of(this)[Util::class.java]
        multiViewAdapter = MultiViewAdapter()
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
        initAdapterFavoriteBeer()
        val listSection = ListSection<DataDB>()
        multiViewAdapter.registerItemBinders(FavoriteItemBinder(util))
        multiViewAdapter.addSection(listSection)
        util.observeSaveBeer().observe(
            viewLifecycleOwner
        ) { data ->
            listSection.set(data)
            if (data.isEmpty())
                fragmentFavoriteBeersBinding.txtEmpty.visibility = View.VISIBLE
            else
                fragmentFavoriteBeersBinding.txtEmpty.visibility = View.GONE
        }
    }

    private fun initAdapterFavoriteBeer() {
        fragmentFavoriteBeersBinding.recycleViewFavorite.apply {
            adapter = multiViewAdapter
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        }
    }
}