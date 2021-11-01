package com.im.solobarbers.views.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.im.solobarbers.R
import com.im.solobarbers.firestore.appointment.Appointment
import com.im.solobarbers.views.dashboard.fragments.appointment.LikeInterface
import kotlinx.android.synthetic.main.appointment_item_layout.view.*

class AppointmentAdapter (private val likeinterface: LikeInterface): RecyclerView.Adapter<AppointmentAdapter.AppointmentViewHolder>() {

    private lateinit var appointment: List<Appointment>

    fun setAppointment(appointment: List<Appointment>) {
        this.appointment = appointment
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppointmentViewHolder {
        return AppointmentViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.appointment_item_layout, parent, false)
        )
    }

    override fun onBindViewHolder(holder: AppointmentViewHolder, position: Int) {
        holder.bind(appointment = appointment[position], likeinterface)
    }

    override fun getItemCount(): Int {
        return appointment.size
    }


    class AppointmentViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {



        fun bind(appointment: Appointment, likeinterface: LikeInterface) {


            itemView.booking_date.text = "Date: ${appointment.date}"

            val position = adapterPosition

            itemView.setOnClickListener {

              if (position == adapterPosition){

                  likeinterface.likeType(appointment.type.toString())

              }

            }

        }

    }


}

