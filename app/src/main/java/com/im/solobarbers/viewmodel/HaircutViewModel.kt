package com.im.solobarbers.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData

import com.im.solobarbers.repository.HaircutRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class HaircutViewModel
@Inject
constructor(
    private val haircutRepository: HaircutRepository
) : ViewModel() {


    val haircutResponse = liveData (Dispatchers.IO) {
        emit(haircutRepository.getHaircuts())
    }

}