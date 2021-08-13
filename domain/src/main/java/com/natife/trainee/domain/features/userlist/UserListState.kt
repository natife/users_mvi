package com.natife.trainee.domain.features.userlist

import com.natife.trainee.domain.model.User

data class UserListState(
    val isLoading: Boolean,
    val users: List<User>
)
