package com.im.solobarbers.views.dashboard.fragments.home.home_fragments

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.im.solobarbers.R
import com.im.solobarbers.databinding.FragmentConfirmBookingBinding
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class ConfirmBooking_Fragment
@Inject
constructor(
    private val fAuth: FirebaseAuth,
    private val fStore: FirebaseFirestore
) : Fragment() {

    private var confirmBinding: FragmentConfirmBookingBinding? = null
    private val binding get() = confirmBinding!!
    private var date: String? = null
    private var type: String? = null
    private var price: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            type = ConfirmBooking_FragmentArgs.fromBundle(it).haircut?.type
            price = ConfirmBooking_FragmentArgs.fromBundle(it).haircut?.price.toString()
        }


    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        confirmBinding = FragmentConfirmBookingBinding.inflate(inflater, container, false)
        val view = binding.root


        Toast.makeText(this.requireContext(), "${fAuth.currentUser?.email}", Toast.LENGTH_LONG)
            .show()

        binding.typeValue.text = type
        binding.priceValue.text = "£$price"

        val cal = Calendar.getInstance()

        val year = cal.get(Calendar.YEAR)
        val calMonth = cal.get(Calendar.MONTH)
        val day = cal.get(Calendar.DAY_OF_MONTH)


        binding.datePicker.setOnClickListener {

            val datepickerDialog = DatePickerDialog(
                this.requireContext(),
                DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->

                    date = "$dayOfMonth/${month + 1}/$year"
                    binding.datePicker.text = date

                },
                year,
                calMonth,
                day
            )

            datepickerDialog.show()

        }

        binding.confirmbookingBtn.setOnClickListener {

            val userId = fAuth.currentUser?.uid
            val userEmail = fAuth.currentUser?.email

            insertBooking(userId, userEmail)

        }


        return view
    }


    private fun insertBooking(userId: String?, userEmail: String?) {


        val booking = hashMapOf(

            "userId" to userId,
            "email" to userEmail,
            "type" to type,
            "price" to price,
            "date" to date

        )

        fStore.collection("booking").document(userId!!)
            .collection("confirmed")
            .add(booking)
            .addOnSuccessListener(
                OnSuccessListener {
                    Log.d("booking", "booking successful: userId: $userId, email: $userEmail")
                    findNavController().navigate(R.id.services_Fragment)
                }
            )
            .addOnFailureListener(
                OnFailureListener { e ->
                    Log.d("booking", "Error: ${e.localizedMessage}")
                }
            )


    }


    override fun onDestroy() {
        super.onDestroy()
        confirmBinding = null
    }


}
