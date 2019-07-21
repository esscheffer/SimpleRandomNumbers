package com.scheffer.erik.simplerandomnumbers.koin

import com.scheffer.erik.simplerandomnumbers.retrofit.RandomOrgService
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single { provideRetrofit() }
    single { provideRandomOrgService(get()) }
}

fun provideRetrofit(): Retrofit {
    return Retrofit.Builder()
        .baseUrl("https://api.random.org/json-rpc/2/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

fun provideRandomOrgService(retrofit: Retrofit): RandomOrgService = retrofit.create(RandomOrgService::class.java)
