package com.im.solobarbers.views.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.im.solobarbers.R
import com.im.solobarbers.firestore.haircut.Haircut
import com.im.solobarbers.views.dashboard.fragments.home.home_fragments.Services_FragmentDirections
import kotlinx.android.synthetic.main.haircut_options_layout.view.*

class HaircutAdapter : RecyclerView.Adapter<HaircutAdapter.HaircutViewHolder>() {

    private lateinit var haircuts: List<Haircut>


    fun setHaircut(haircuts: List<Haircut>) {
        this.haircuts = haircuts
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HaircutViewHolder {
        return HaircutViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.haircut_options_layout, parent, false)
        )
    }

    override fun onBindViewHolder(holder: HaircutViewHolder, position: Int) {
        holder.bind(haircut = haircuts[position])
    }

    override fun getItemCount(): Int {
        return haircuts.size
    }

    class HaircutViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {

        fun bind(haircut: Haircut) {

            itemView.type.text = haircut.type
            itemView.price.text = "£${haircut.price}"


            itemView.book.setOnClickListener {

                val position = adapterPosition

                if (position == adapterPosition) {


                    val action = Services_FragmentDirections.actionServicesFragmentToConfirmBookingFragment()
                    action.haircut = haircut
                    Navigation.findNavController(it).navigate(action)


                }

            }

        }
    }
}