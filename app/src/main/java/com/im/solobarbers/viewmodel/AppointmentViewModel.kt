package com.im.solobarbers.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.im.solobarbers.repository.HaircutRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class AppointmentViewModel
@Inject
constructor(
    private val haircutRepository: HaircutRepository
) : ViewModel() {


    val appointmentResponse = liveData(Dispatchers.IO) {
        emit(haircutRepository.getAppointments())
    }

}