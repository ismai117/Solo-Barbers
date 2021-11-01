package com.im.solobarbers.views.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.im.solobarbers.R
import com.im.solobarbers.firestore.favourite.Favourite
import kotlinx.android.synthetic.main.favourite_item_layout.view.*

class FavouriteAdapter : RecyclerView.Adapter<FavouriteAdapter.FavouriteViewHolder>() {

    private lateinit var favourite: List<Favourite>

    fun setFavourite(favourite: List<Favourite>) {
        this.favourite = favourite
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavouriteViewHolder {
        return FavouriteViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.favourite_item_layout, parent, false)
        )
    }

    override fun onBindViewHolder(holder: FavouriteViewHolder, position: Int) {
        holder.bind(favourite = favourite[position])
    }

    override fun getItemCount(): Int {
        return favourite.size
    }

    class FavouriteViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {

        fun bind(favourite: Favourite) {

            itemView.favourite_type.text = favourite.liked

        }

    }


}