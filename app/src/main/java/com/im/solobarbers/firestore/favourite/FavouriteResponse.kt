package com.im.solobarbers.firestore.favourite

import java.lang.Exception

data class FavouriteResponse(
    var favourite: List<Favourite>? = null,
    var exception: Exception? = null
){
    constructor() : this(null, null)
}
