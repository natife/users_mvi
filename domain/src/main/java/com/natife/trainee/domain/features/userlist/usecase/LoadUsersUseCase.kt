package com.natife.trainee.domain.features.userlist.usecase

import com.natife.trainee.core.coroutines.AppDispatchers
import com.natife.trainee.core.mvi.UseCase
import com.natife.trainee.core.utils.Result
import com.natife.trainee.domain.features.userlist.UserListAction
import com.natife.trainee.domain.features.userlist.UserListState
import com.natife.trainee.domain.repository.UserRepository

class LoadUsersUseCase(
    private val userRepository: UserRepository,
    dispatchers: AppDispatchers
) : UseCase<UserListAction, UserListState, UserListAction.LoadUsers>(dispatchers) {

    override suspend fun invoke(
        action: UserListAction.LoadUsers,
        state: UserListState
    ): UserListAction {
        return when (val result = userRepository.getUsers()) {
            is Result.Success -> UserListAction.UsersLoaded(state.users + result.data)
            is Result.Error -> UserListAction.Error(result.error)
        }
    }
}