package com.scheffer.erik.simplerandomnumbers.retrofit

import com.scheffer.erik.simplerandomnumbers.retrofit.models.RandomOrgGenerateIntegerPostModel
import com.scheffer.erik.simplerandomnumbers.retrofit.models.RandomOrgGenerateIntegerReturnModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface RandomOrgService {
    @POST("invoke")
    fun invoke(@Body randomOrgGenerateIntegerPostModel: RandomOrgGenerateIntegerPostModel)
            : Call<RandomOrgGenerateIntegerReturnModel>
}