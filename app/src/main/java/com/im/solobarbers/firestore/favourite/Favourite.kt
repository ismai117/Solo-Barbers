package com.im.solobarbers.firestore.favourite

data class Favourite(
    val liked: String? = null
) {
    constructor() : this(null)
}
