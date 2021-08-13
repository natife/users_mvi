package com.natife.trainee.users.data.network.model

import com.google.gson.annotations.SerializedName

data class UsersResponse(
    @SerializedName("results")
    val results: List<UserDto>
)
