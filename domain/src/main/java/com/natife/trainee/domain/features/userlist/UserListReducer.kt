package com.natife.trainee.domain.features.userlist

import com.natife.trainee.core.mvi.Reducer

class UserListReducer : Reducer<UserListAction, UserListState> {

    override val initialState = UserListState(
        isLoading = false,
        users = listOf()
    )

    override fun reduce(action: UserListAction, state: UserListState): UserListState {
        return when(action) {
            UserListAction.None -> state
            UserListAction.LoadUsers -> state.copy(
                isLoading = true
            )
            is UserListAction.UsersLoaded -> state.copy(
                isLoading = false,
                users = action.users
            )
            is UserListAction.Error -> state.copy(
                isLoading = false
            )
        }
    }
}