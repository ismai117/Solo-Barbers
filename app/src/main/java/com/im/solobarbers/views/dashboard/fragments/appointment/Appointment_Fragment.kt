package com.im.solobarbers.views.dashboard.fragments.appointment

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.im.solobarbers.R
import com.im.solobarbers.databinding.FragmentAppointmentBinding
import com.im.solobarbers.viewmodel.AppointmentViewModel
import com.im.solobarbers.views.adapters.AppointmentAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Appointment_Fragment : Fragment(), LikeInterface {

    private var appointmentBinding: FragmentAppointmentBinding? = null
    private val binding get() = appointmentBinding!!
    private val appointmentModel: AppointmentViewModel by viewModels()
    private lateinit var appointmentAdapter: AppointmentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appointmentAdapter = AppointmentAdapter(this)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        appointmentBinding = FragmentAppointmentBinding.inflate(inflater, container, false)
        val view = binding.root


        appointmentModel.appointmentResponse.observe(this.viewLifecycleOwner) { response ->

            response?.let {

                if (it.appointment != null) {
                    initRecycler()
                    appointmentAdapter.setAppointment(it.appointment!!)
                    binding.appointmentProgressBar.visibility = View.GONE
                } else {
                    Log.d("appointment", "${it.exception?.localizedMessage}")
                    binding.appointmentProgressBar.visibility = View.GONE
                }

            }

        }

        binding.appointmentProgressBar.visibility = View.VISIBLE



        return view
    }


    private fun initRecycler() {
        binding.appointmentRecyclerView.layoutManager = LinearLayoutManager(this.requireContext())
        binding.appointmentRecyclerView.setHasFixedSize(true);
        binding.appointmentRecyclerView.adapter = appointmentAdapter
    }

    override fun likeType(type: String) {
        Toast.makeText(this.requireContext(), "$type", Toast.LENGTH_LONG).show()


        val dialog = CustomDialogFragment(type)


        dialog.show(childFragmentManager, "custom Dialog")


    }


    override fun onDestroy() {
        super.onDestroy()
        appointmentBinding = null
    }

}

