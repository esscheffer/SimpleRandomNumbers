package com.scheffer.erik.simplerandomnumbers.retrofit.models

data class Result(
    val advisoryDelay: Int,
    val bitsLeft: Int,
    val bitsUsed: Int,
    val random: Random,
    val requestsLeft: Int
)