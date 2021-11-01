package com.im.solobarbers.repository


import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.ktx.Firebase
import com.im.solobarbers.firestore.appointment.Appointment
import com.im.solobarbers.firestore.appointment.AppointmentResponse
import com.im.solobarbers.firestore.favourite.Favourite
import com.im.solobarbers.firestore.favourite.FavouriteResponse

import com.im.solobarbers.firestore.haircut.Haircut
import com.im.solobarbers.firestore.haircut.HaircutResponse


import kotlinx.coroutines.tasks.await
import java.lang.Exception
import javax.inject.Inject


class HaircutRepository
@Inject
constructor(
    firestore: FirebaseFirestore,
    fAuth: FirebaseAuth
) {

    private val userId = fAuth.currentUser?.uid.toString()

    var haircutRef: CollectionReference = firestore.collection("options")
    var appointmentRef: CollectionReference =
        firestore.collection("booking").document(userId).collection("confirmed")
    var favouriteRef: CollectionReference =
        firestore.collection("favourite").document(userId).collection("liked")


    suspend fun getHaircuts(): HaircutResponse {

        val haircutResponse = HaircutResponse()

        try {
            haircutResponse.haircut = haircutRef.orderBy("type", Query.Direction.ASCENDING).get()
                .await().documents.mapNotNull { documentSnapshot ->
                    documentSnapshot.toObject(Haircut::class.java)
                }
        } catch (exeption: Exception) {
            haircutResponse.exception = exeption
        }

        return haircutResponse
    }


    suspend fun getAppointments(): AppointmentResponse {

        val appointmentResponse = AppointmentResponse()

        try {
            appointmentResponse.appointment =
                appointmentRef.get().await().documents.mapNotNull { documentSnapshot ->
                    documentSnapshot.toObject(Appointment::class.java)
                }
        } catch (exception: Exception) {
            appointmentResponse.exception = exception
        }

        return appointmentResponse

    }


    suspend fun getFavourites(): FavouriteResponse {

        val favouriteResponse = FavouriteResponse()

        try {
            favouriteResponse.favourite =
                favouriteRef.get().await().documents.mapNotNull { documentSnapshot ->
                    documentSnapshot.toObject(Favourite::class.java)
                }
        } catch (exceprion: Exception) {
            favouriteResponse.exception = exceprion
        }

        return favouriteResponse
    }


}


//}