package com.im.solobarbers.firestore.haircut

import java.io.Serializable
import java.lang.Exception

data class HaircutResponse(
    var haircut: List<Haircut>? = null,
    var exception: Exception? = null,
) : Serializable {
    constructor() : this(null, null)
}
