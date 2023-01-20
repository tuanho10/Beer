package com.example.beer.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.beer.data.model.Data
import com.example.beer.data.model.DataDB
import com.example.beer.databinding.BeerItemBinding
import com.example.beer.util.Util

class BeerRecyclerAdapter(util: Util) : RecyclerView.Adapter<BeerRecyclerAdapter.BeerViewHolder>() {
    private var beers: List<Data> = ArrayList()
    private var utilAdapter = util
    private var contextAdapter: Context? = null

    fun setDataBeers(beersList: List<Data>) {
        this.beers = beersList
        notifyDataSetChanged()
    }

    class BeerViewHolder(val binding: BeerItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeerViewHolder {
        contextAdapter = parent.context
        return BeerViewHolder(
            BeerItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: BeerViewHolder, position: Int) {
        holder.binding.apply {
            tvBeerName.text = beers[position].name
            tvBeerPrice.text = beers[position].price
            Glide.with(holder.itemView)
                .load(beers[position].image)
                .into(imgBeer)
            btnSave.setOnClickListener {
                if (!utilAdapter.isBeerSavedInDatabase(beers[position].id)) {
                    val dataDB = DataDB(
                        beers[position].id,
                        beers[position].name,
                        beers[position].price,
                        beers[position].image,
                        editNote.text.toString()
                    )
                    utilAdapter.insertBeer(dataDB)
                    editNote.setText("")
                    Toast.makeText(contextAdapter, "Item has been saved", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(contextAdapter, "Already save this item", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return beers.size
    }
}