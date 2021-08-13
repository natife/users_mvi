package com.natife.trainee.users.data.network.model

import com.google.gson.annotations.SerializedName

data class TimeZoneDto(
    @SerializedName("offset")
    val offset: String,
    @SerializedName("description")
    val description: String
)
