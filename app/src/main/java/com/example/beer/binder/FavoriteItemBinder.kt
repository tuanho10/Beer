package com.example.beer.binder

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.beer.R
import com.example.beer.data.model.DataDB
import com.example.beer.util.Util
import mva2.adapter.ItemBinder
import mva2.adapter.ItemViewHolder

class FavoriteItemBinder(util: Util) : ItemBinder<DataDB, FavoriteItemBinder.ViewHolder>() {
    private var utilAdapter = util
    private var contextAdapter: Context? = null

    override fun createViewHolder(parent: ViewGroup): ViewHolder {
        contextAdapter = parent.context
        return ViewHolder(inflate(parent, R.layout.favorite_beer_item))
    }

    override fun bindViewHolder(holder: ViewHolder, favoriteBeers: DataDB) {
        holder.apply {
            tvFavoriteName.text = favoriteBeers.name
            tvFavoritePrice.text = favoriteBeers.price
            note.setText(favoriteBeers.note)
            Glide.with(holder.itemView)
                .load(favoriteBeers.image)
                .into(image)
            note.setOnClickListener {
                note.isCursorVisible = true
            }
            buttonUpdate.setOnClickListener {
                val data = DataDB(
                    favoriteBeers.id,
                    favoriteBeers.name,
                    favoriteBeers.price,
                    favoriteBeers.image,
                    note.text.toString()
                )
                utilAdapter.updateBeer(data)
                note.isCursorVisible = false
                Toast.makeText(
                    contextAdapter,
                    contextAdapter!!.resources.getString(R.string.item_update),
                    Toast.LENGTH_SHORT
                ).show()
            }
            buttonDelete.setOnClickListener {
                utilAdapter.deleteBeerById(favoriteBeers.id)
                Toast.makeText(
                    contextAdapter,
                    contextAdapter!!.resources.getString(R.string.item_delete),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    override fun canBindData(item: Any): Boolean {
        return item is DataDB
    }

    class ViewHolder(itemView: View) :
        ItemViewHolder<DataDB?>(itemView) {
        var tvFavoriteName: TextView
        var tvFavoritePrice: TextView
        var image: ImageView
        var buttonUpdate: Button
        var buttonDelete: Button
        var note: EditText

        init {
            tvFavoriteName = itemView.findViewById(R.id.txtFavoriteName)
            tvFavoritePrice = itemView.findViewById(R.id.txtFavoritePrice)
            image = itemView.findViewById(R.id.imgFavoriteBeer)
            buttonUpdate = itemView.findViewById(R.id.btnUpdate)
            buttonDelete = itemView.findViewById(R.id.btnDelete)
            note = itemView.findViewById(R.id.editFavorite)
        }
    }
}