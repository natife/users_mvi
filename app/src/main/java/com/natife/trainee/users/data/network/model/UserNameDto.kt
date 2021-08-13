package com.natife.trainee.users.data.network.model

import com.google.gson.annotations.SerializedName

data class UserNameDto(
    @SerializedName("title")
    val title: String,
    @SerializedName("first")
    val firstName: String,
    @SerializedName("last")
    val lastName: String
)
