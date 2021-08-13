package com.natife.trainee.users.data.network.model

import com.google.gson.annotations.SerializedName

data class UserDate(
    @SerializedName("date")
    val date: String,
    @SerializedName("age")
    val age: Int
)
