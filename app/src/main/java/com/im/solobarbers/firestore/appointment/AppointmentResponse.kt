package com.im.solobarbers.firestore.appointment

import java.io.Serializable
import java.lang.Exception

class AppointmentResponse(
    var appointment: List<Appointment>? = null,
    var exception: Exception? = null
) : Serializable {
    constructor() : this(null, null)
}