package com.natife.trainee.domain.features.userdetails

import com.natife.trainee.domain.model.User

data class UserDetailsState(
    val isLoading: Boolean,
    val userId: String,
    val user: User?
)