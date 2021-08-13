package com.natife.trainee.domain.features.userlist

import com.natife.trainee.domain.model.User

sealed class UserListAction {

    object None : UserListAction()
    object LoadUsers : UserListAction()
    data class UsersLoaded(val users: List<User>) : UserListAction()
    data class Error(val cause: Throwable) : UserListAction()

}
