package com.natife.trainee.domain.features.userdetails

import com.natife.trainee.core.mvi.Reducer

class UserDetailsReducer(
    userId: String
) : Reducer<UserDetailsAction, UserDetailsState> {

    override val initialState = UserDetailsState(
        isLoading = false,
        userId = userId,
        user = null
    )

    override fun reduce(action: UserDetailsAction, state: UserDetailsState): UserDetailsState {
        return when(action) {
            UserDetailsAction.None -> state
            UserDetailsAction.LoadUser -> state.copy(
                isLoading = true
            )
            is UserDetailsAction.UserLoaded -> state.copy(
                isLoading = false,
                user = action.user
            )
            is UserDetailsAction.Error -> state.copy(
                isLoading = false
            )
        }
    }
}