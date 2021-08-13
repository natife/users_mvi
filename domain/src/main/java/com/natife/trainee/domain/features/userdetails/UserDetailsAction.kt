package com.natife.trainee.domain.features.userdetails

import com.natife.trainee.domain.model.User

sealed class UserDetailsAction {

    object None : UserDetailsAction()
    object LoadUser : UserDetailsAction()
    data class UserLoaded(val user: User) : UserDetailsAction()
    data class Error(val cause: Throwable) : UserDetailsAction()

}
