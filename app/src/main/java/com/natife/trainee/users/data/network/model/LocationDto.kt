package com.natife.trainee.users.data.network.model

import com.google.gson.annotations.SerializedName

data class LocationDto(
    @SerializedName("street")
    val street: StreetDto,
    @SerializedName("city")
    val city: String,
    @SerializedName("state")
    val state: String,
    @SerializedName("country")
    val country: String,
    @SerializedName("coordinates")
    val coordinates: CoordinatesDto,
    @SerializedName("timezone")
    val timeZone: TimeZoneDto
)
