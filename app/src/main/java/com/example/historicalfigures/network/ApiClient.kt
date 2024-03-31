package com.example.historicalfigures.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private const val BASE_URL = "https://api.api-ninjas.com/v1/"
    private const val API_KEY = "SNFNkTTxEhBVdg/dCYi4UQ==LCCXxMqV6tf1dEeT"
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val HistoricalFigureServiceInstance: HistoricalFigureService = retrofit.create(HistoricalFigureService::class.java)
}