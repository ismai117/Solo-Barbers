package com.im.solobarbers.firestore.appointment

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Appointment(
    var date: String? = null,
    var email: String? = null,
    var price: String? = null,
    var type: String? = null,
    var userId: String? = null

) : Serializable {
    constructor() : this("", "", "", "", "")
}