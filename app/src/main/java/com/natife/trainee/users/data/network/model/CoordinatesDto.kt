package com.natife.trainee.users.data.network.model

import com.google.gson.annotations.SerializedName

data class CoordinatesDto(
    @SerializedName("latitude")
    val latitude: String,
    @SerializedName("longitude")
    val longitude: String
)
