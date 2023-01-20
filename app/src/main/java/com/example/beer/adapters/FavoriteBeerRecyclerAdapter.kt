package com.example.beer.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.beer.data.model.DataDB
import com.example.beer.databinding.FavoriteBeerItemBinding
import com.example.beer.util.Util

class FavoriteBeerRecyclerAdapter(util: Util) :
    RecyclerView.Adapter<FavoriteBeerRecyclerAdapter.FavoriteViewHolder>() {
    private var favoriteBeers: List<DataDB> = ArrayList()
    private var utilAdapter = util
    private var contextAdapter: Context? = null
    fun setFavoriteBeers(favoriteBeersList: List<DataDB>) {
        this.favoriteBeers = favoriteBeersList
        notifyDataSetChanged()
    }

    class FavoriteViewHolder(val binding: FavoriteBeerItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        contextAdapter = parent.context
        return FavoriteViewHolder(
            FavoriteBeerItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.binding.apply {
            tvFavoriteName.text = favoriteBeers[position].name
            tvFavoritePrice.text = favoriteBeers[position].price
            editFavorite.setText(favoriteBeers[position].note)
            Glide.with(holder.itemView)
                .load(favoriteBeers[position].image)
                .into(imgFavoriteBeer)
            editFavorite.setOnClickListener {
                editFavorite.isCursorVisible = true
            }
            btnUpdate.setOnClickListener {
                val data = DataDB(
                    favoriteBeers[position].id,
                    favoriteBeers[position].name,
                    favoriteBeers[position].price,
                    favoriteBeers[position].image,
                    editFavorite.text.toString()
                )
                utilAdapter.updateBeer(data)
                editFavorite.isCursorVisible = false
                Toast.makeText(contextAdapter, "Item has been updated", Toast.LENGTH_SHORT).show()
            }
            btnDelete.setOnClickListener {
                utilAdapter.deleteBeerById(favoriteBeers[position].id)
                Toast.makeText(contextAdapter, "Item has been deleted", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun getItemCount(): Int {
        return favoriteBeers.size
    }
}