package com.example.historicalfigures.network

import com.example.historicalfigures.model.entity.HistoricalFigure
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface HistoricalFigureService {
    @GET("historicalfigures")
    fun fetchHistoricalFigureList(@Query("name") name: String,
                                  @Query("X-Api-Key") apiKey: String)
    : Call<List<HistoricalFigure>>
}

// https://api.api-ninjas.com/v1/historicalfigures
