package com.madeit.harrypotter.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private const val BASE_URL = "https://api.potterdb.com/v1/"

//    https://api.potterdb.com/v1/movies/{bb71cc0d-32b7-4a05-876b-4774064a5cec}

    private fun createRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun createAllHarryPotterBooksApi(): HarryPotterApi {
        return createRetrofit().create(HarryPotterApi::class.java)
    }

}