package com.example.historicalfigures.model.entity

import com.google.gson.annotations.SerializedName


data class HistoricalFigure (
    val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("title") val title: String,
    @SerializedName("info") val info: HistoricalFigureInfo,
    @SerializedName("language") val language: String,
)