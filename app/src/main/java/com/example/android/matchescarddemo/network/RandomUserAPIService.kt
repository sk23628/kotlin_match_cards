package com.example.android.matchescarddemo.network

import com.example.android.matchescarddemo.data.RandomUser
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://randomuser.me/"

private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

private val retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .baseUrl(BASE_URL)
        .build()

interface RandomUserAPIService {
    @GET("api/")
    fun getUsers(@Query("results") results : Int):
            Deferred<RandomUser>
}

object RandomUserApi {
    val retrofitService : RandomUserAPIService by lazy {
        retrofit.create(RandomUserAPIService::class.java)
    }

}