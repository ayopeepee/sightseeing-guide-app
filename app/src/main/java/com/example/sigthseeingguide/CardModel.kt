package com.example.sigthseeingguide

data class CardModel(
    val cardName: String,
    val cardDesc: String,
    val cardImage: String,
    val latitude: String,
    val longitude: String,
    val info: String
) {
    constructor(): this(
        "",
        "",
        "",
        "",
        "",
        ""
    )
}
