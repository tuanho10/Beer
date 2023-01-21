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
import com.example.beer.data.model.Data
import com.example.beer.data.model.DataDB
import com.example.beer.util.Util
import mva2.adapter.ItemBinder
import mva2.adapter.ItemViewHolder

class BeerItemBinder(util: Util) : ItemBinder<Data, BeerItemBinder.ViewHolder>() {
    private var utilAdapter = util
    private var contextAdapter: Context? = null

    override fun createViewHolder(parent: ViewGroup): ViewHolder {
        contextAdapter = parent.context
        return ViewHolder(inflate(parent, R.layout.beer_item))
    }

    override fun bindViewHolder(holder: ViewHolder, beers: Data) {
        holder.apply {
            tvBeerName.text = beers.name
            tvBeerPrice.text = beers.price
            Glide.with(holder.itemView)
                .load(beers.image)
                .into(image)
            buttonSave.isEnabled = !utilAdapter.isBeerSavedInDatabase(beers.id)
            buttonSave.setOnClickListener {
                if (!utilAdapter.isBeerSavedInDatabase(beers.id)) {
                    val dataDB = DataDB(
                        beers.id,
                        beers.name,
                        beers.price,
                        beers.image,
                        note.text.toString()
                    )
                    utilAdapter.insertBeer(dataDB)
                    note.setText("")
                    Toast.makeText(
                        contextAdapter,
                        contextAdapter!!.resources.getString(R.string.item_save),
                        Toast.LENGTH_SHORT
                    ).show()
                    buttonSave.isEnabled = false
                } else {
                    Toast.makeText(
                        contextAdapter,
                        contextAdapter!!.resources.getString(R.string.item_already_save),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    override fun canBindData(item: Any): Boolean {
        return item is Data
    }

    class ViewHolder(itemView: View) :
        ItemViewHolder<Data?>(itemView) {
        var tvBeerName: TextView
        var tvBeerPrice: TextView
        var image: ImageView
        var buttonSave: Button
        var note: EditText

        init {
            tvBeerName = itemView.findViewById(R.id.txtBeerName)
            tvBeerPrice = itemView.findViewById(R.id.txtBeerPrice)
            image = itemView.findViewById(R.id.imgBeer)
            buttonSave = itemView.findViewById(R.id.btnSave)
            note = itemView.findViewById(R.id.editNote)
        }
    }
}