package com.im.solobarbers.views.dashboard.fragments.appointment

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.im.solobarbers.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.custom_dialog.view.*
import kotlinx.android.synthetic.main.haircut_options_layout.*
import javax.inject.Inject

@AndroidEntryPoint
class CustomDialogFragment(val type: String) : DialogFragment() {


    @Inject
    lateinit var fStore: FirebaseFirestore

    @Inject
    lateinit var fAuth: FirebaseAuth


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.custom_dialog, container, false)

        view.favourite_value.text = type

        val uid = fAuth.currentUser?.uid.toString()

        view.like_type.setOnClickListener {


            val liked = hashMapOf(
                "liked" to type,
                "userid" to uid
            )

            fStore.collection("favourite").document(uid).collection("liked")
                .add(liked)
                .addOnSuccessListener(
                    OnSuccessListener {
                        Log.d("liked", "success")
                    }
                )
                .addOnFailureListener(
                    OnFailureListener { e ->
                        Log.d("liked", "Error: ${e.localizedMessage}")
                    }
                )



            Toast.makeText(requireContext(), "$type", Toast.LENGTH_LONG).show()
        }

        view.cancel.setOnClickListener {
            dialog?.cancel()
        }



        return view
    }


    override fun onStart() {
        super.onStart()
        val width = (resources.displayMetrics.widthPixels * 0.9).toInt()
        val height = (resources.displayMetrics.heightPixels * 0.2).toInt()
        dialog!!.window?.setLayout(width, height)
    }

}