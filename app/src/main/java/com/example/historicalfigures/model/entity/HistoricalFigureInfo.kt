package com.example.historicalfigures.model.entity

import com.google.gson.annotations.SerializedName

data class HistoricalFigureInfo(
    @SerializedName("born") val born: String,
    @SerializedName("died") val died: String,
    @SerializedName("nationality") val nationality: String,
    @SerializedName("years_active") val years_active: String,
)
