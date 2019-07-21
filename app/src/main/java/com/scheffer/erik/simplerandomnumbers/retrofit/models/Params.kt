package com.scheffer.erik.simplerandomnumbers.retrofit.models

import com.scheffer.erik.simplerandomnumbers.BuildConfig

data class Params(
    val apiKey: String = BuildConfig.API_KEY,
    val base: Int = 10,
    val min: Int = 1,
    val max: Int = 100,
    val n: Int = 1,
    val replacement: Boolean = true
)