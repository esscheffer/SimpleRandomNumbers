package com.scheffer.erik.simplerandomnumbers.retrofit.models

import kotlin.random.Random

data class RandomOrgGenerateIntegerPostModel(
    val params: Params,
    val id: Int = Random.nextInt(0, 10000),
    val jsonrpc: String = "2.0",
    val method: String = "generateIntegers"
)