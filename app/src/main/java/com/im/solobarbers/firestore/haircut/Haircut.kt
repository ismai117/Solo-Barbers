package com.im.solobarbers.firestore.haircut

import java.io.Serializable

data class Haircut(
    val type: String? = null,
    val price: Int? = null,
) : Serializable {
    constructor() : this(null, null)
}