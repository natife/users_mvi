package com.natife.trainee.users.data.network.model

import com.google.gson.annotations.SerializedName

data class StreetDto(
    @SerializedName("number")
    val number: Int,
    @SerializedName("name")
    val name: String
)
