package com.example.sigthseeingguide

data class CardModel(
    val cardName: String,
    val cardDesc: String,
    val cardImage: String
) {
    constructor(): this(
        "",
        "",
        ""
    )
}
